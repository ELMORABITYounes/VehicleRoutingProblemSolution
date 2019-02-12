package com.elmorabit.ensak.security;

import com.elmorabit.ensak.domain.AppUser;
import com.elmorabit.ensak.dto.LoginForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthentictionFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthentictionFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginForm form=null;
        try {
            form = new ObjectMapper().readValue(request.getInputStream(), LoginForm.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.getUsername(),form.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AppUser appUser=(AppUser) authResult.getPrincipal();
        String jwt= Jwts.builder()
                .setSubject(appUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+ SecurityConsts.EXPIRATION_DATE))
                .signWith(SignatureAlgorithm.HS256, SecurityConsts.SECRET)
                .claim("roles",appUser.getAuthorities())
                .claim("firstName",appUser.getFirstName())
                .claim("lastName",appUser.getLastName())
                .compact();
        response.addHeader(SecurityConsts.HEADER_STRING, SecurityConsts.TOKEN_PREFIX+jwt);
    }
}
