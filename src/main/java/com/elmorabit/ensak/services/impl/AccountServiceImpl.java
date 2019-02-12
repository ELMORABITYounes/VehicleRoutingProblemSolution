package com.elmorabit.ensak.services.impl;

import com.elmorabit.ensak.repository.AuthorityRepository;
import com.elmorabit.ensak.repository.UserRepository;
import com.elmorabit.ensak.domain.AppAuthority;
import com.elmorabit.ensak.domain.AppUser;
import com.elmorabit.ensak.security.SecurityConsts;
import com.elmorabit.ensak.security.SecurityUtils;
import com.elmorabit.ensak.services.AccountService;
import com.elmorabit.ensak.web.rest.errors.InvalidPasswordException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;



    @Override
    public AppUser saveUser(AppUser user) {
        String hashPW=bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPW);
        return userRepository.save(user);
    }

    @Override
    public AppAuthority saveAuthority(AppAuthority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public void addAuthorityToUser(Long id, String authority) {
        AppAuthority appAuthority=authorityRepository.findByAuthority(authority);
        if(appAuthority==null) {
            appAuthority = new AppAuthority(authority);
            authorityRepository.save(appAuthority);
        }
        Optional<AppUser> userOptional=userRepository.findById(id);
        if (userOptional.isPresent())
            userOptional.get().addAuthority(appAuthority);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils.getCurrentUserLogin()
                .flatMap(userRepository::findOneByUsername)
                .ifPresent(user -> {
                    String currentEncryptedPassword = user.getPassword();
                    if (!bCryptPasswordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                        throw new InvalidPasswordException();
                    }
                    String encryptedPassword = bCryptPasswordEncoder.encode(newPassword);
                    user.setPassword(encryptedPassword);
                    userRepository.save(user);
                    log.debug("Changed password for User: {}", user);
                });
    }

    @Override
    public String refreshToken() {
        String jwt=null;
        Optional<AppUser> optionalAppUser=SecurityUtils.getCurrentUserLogin()
                .flatMap(userRepository::findOneByUsername);
        if (optionalAppUser.isPresent())
            jwt=Jwts.builder()
                    .setSubject(optionalAppUser.get().getUsername())
                    .setExpiration(new Date(System.currentTimeMillis()+ SecurityConsts.EXPIRATION_DATE))
                    .signWith(SignatureAlgorithm.HS256, SecurityConsts.SECRET)
                    .claim("roles",optionalAppUser.get().getAuthorities())
                    .claim("firstName",optionalAppUser.get().getFirstName())
                    .claim("lastName",optionalAppUser.get().getLastName())
                    .compact();
        return jwt;
    }

}
