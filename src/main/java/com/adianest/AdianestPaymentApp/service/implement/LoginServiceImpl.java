/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.jwt.JwtConfig;
import com.adianest.AdianestPaymentApp.jwt.JwtResponseBody;
import com.adianest.AdianestPaymentApp.jwt.UsernameAndPasswordAuthenticationRequest;
import com.adianest.AdianestPaymentApp.service.ILoginService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class LoginServiceImpl implements ILoginService {

    private final JwtConfig jwtConfig;

    LoginServiceImpl(
            JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public JwtResponseBody doLoginUser(UsernameAndPasswordAuthenticationRequest authenticationRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Date expirationDate = java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getExpirationDateAfterDays()));
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(jwtConfig.getSigningSecretKey())
                .compact();

        JwtResponseBody responseBody = new JwtResponseBody();
        responseBody.setToken(token);
        responseBody.setExpirationDate(expirationDate);

        return responseBody;
    }
}
