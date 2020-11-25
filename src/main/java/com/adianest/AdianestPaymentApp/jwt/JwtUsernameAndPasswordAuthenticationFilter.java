package com.adianest.AdianestPaymentApp.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
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
import java.time.LocalDate;
import java.util.Date;


// this class to verify client credentials
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;

    public JwtUsernameAndPasswordAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JwtConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
    }


    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {

        try {
            UsernameAndPasswordAuthenticationRequest usernameAndPasswordAuthenticationRequest =
                    new ObjectMapper()
                            .readValue(
                                request.getInputStream(),
                                UsernameAndPasswordAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    usernameAndPasswordAuthenticationRequest.getUsername(),
                    usernameAndPasswordAuthenticationRequest.getPassword()
            );

            return authenticationManager.authenticate(authentication);
        }catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

//    @Override
//    protected void successfulAuthentication(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain chain,
//            Authentication authResult) throws IOException, ServletException {
//
//        Date expirationDate = java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getExpirationDateAfterDays()));
//        String token = Jwts.builder()
//                .setSubject(authResult.getName())
//                .claim("authorities", authResult.getAuthorities())
//                .setIssuedAt(new Date())
//                .setExpiration(expirationDate)
//                .signWith(jwtConfig.getSigningSecretKey())
//                .compact();
//
//        JwtResponseBody responseBody = new JwtResponseBody();
//        responseBody.setToken(token);
//        responseBody.setExpirationDate(expirationDate);
//
//        response.addHeader(jwtConfig.getTokenHeaderName(), token);
//        response.addHeader(jwtConfig.getExpirationDateHeaderName(), expirationDate.toString());
//        response.setCharacterEncoding(jwtConfig.getCharacterEncoding());
//        response.setContentType(jwtConfig.getContentType());
//        response.getWriter()
//                .write(new ObjectMapper().writeValueAsString(responseBody));
//
//    }


}
