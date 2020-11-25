package com.adianest.AdianestPaymentApp.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifierFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;

    public JwtTokenVerifierFilter(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

        // cek if authorization is present in request. if not reject request
        if (authorizationHeader == null || authorizationHeader.isEmpty() || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try{
            // get token from header
            String token = authorizationHeader.replace("Bearer ", "");

            Jws<Claims> claimsJws = Jwts
                    .parserBuilder()
                    .setSigningKey(jwtConfig.getSigningSecretKey())
                    .build()
                    .parseClaimsJws(token);

            System.out.println("Signature: " + claimsJws.getSignature());
            System.out.println("Header: " + new ObjectMapper().writeValueAsString(claimsJws.getHeader()));
            System.out.println("Claims: " + new ObjectMapper().writeValueAsString(claimsJws.getBody()));

            Claims body = claimsJws.getBody();

            System.out.println("body: " + body.get("authorities"));

            // get username
            String username = body.getSubject();

            // map authorities from jwt to a list
            @SuppressWarnings("unchecked")
            List<Map<String, String>> authoritiesMappings = (List<Map<String, String>>) body.get("authorities");

            // extract user authority to a set
            Set<SimpleGrantedAuthority> authority = authoritiesMappings.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());

            // set username & authority to spring authentication
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authority
            );

            // set authentication to spring security. so user authenticated can be true
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }catch (JwtException e) {

            throw new IllegalStateException(String.format("Token cannot be trusted. %s", e.getMessage()));
        }

        filterChain.doFilter(request, response);
    }
}
