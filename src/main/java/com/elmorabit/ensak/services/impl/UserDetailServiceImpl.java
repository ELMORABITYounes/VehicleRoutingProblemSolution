package com.elmorabit.ensak.services.impl;

import com.elmorabit.ensak.domain.AppUser;
import com.elmorabit.ensak.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user=accountService.findUserByUsername(username);
         if (user==null)
             throw new UsernameNotFoundException(username);

        return user;
    }
}
