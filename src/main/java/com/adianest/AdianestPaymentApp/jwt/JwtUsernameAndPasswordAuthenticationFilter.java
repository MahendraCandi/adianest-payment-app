package com.adianest.AdianestPaymentApp.jwt;

import com.adianest.AdianestPaymentApp.model.ResponseMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    private static final Logger log = LogManager.getLogger(JwtUsernameAndPasswordAuthenticationFilter.class);

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

            if (usernameAndPasswordAuthenticationRequest.getUsername().isEmpty() ||
                    usernameAndPasswordAuthenticationRequest.getPassword().isEmpty()) {
                throw new NullPointerException("Username or password is empty");
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    usernameAndPasswordAuthenticationRequest.getUsername(),
                    usernameAndPasswordAuthenticationRequest.getPassword()
            );

            return authenticationManager.authenticate(authentication);

        }catch (IOException | NullPointerException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        Date expirationDate = java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getExpirationDateAfterDays()));
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(jwtConfig.getSigningSecretKey())
                .compact();

        JwtResponseBody responseBody = new JwtResponseBody();
        responseBody.setToken(token);
        responseBody.setExpirationDate(expirationDate);

        response.setCharacterEncoding(jwtConfig.getCharacterEncoding());
        response.setContentType(jwtConfig.getContentType());
        response.getWriter()
                .write(new ObjectMapper().writeValueAsString(responseBody));
        response.setStatus(HttpServletResponse.SC_OK);
        
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        ResponseMessage rm = new ResponseMessage();
        rm.setStatus(HttpStatus.FORBIDDEN.value());
        rm.setMessage(failed.getMessage());

        response.setCharacterEncoding(jwtConfig.getCharacterEncoding());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter()
                .write(new ObjectMapper().writeValueAsString(rm));
        response.setStatus(HttpStatus.FORBIDDEN.value());

    }


}
