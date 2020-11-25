package com.adianest.AdianestPaymentApp.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
    private String secretKey;
    private String tokenHeaderName;
    private String expirationDateHeaderName;
    private Integer expirationDateAfterDays;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getTokenHeaderName() {
        return tokenHeaderName;
    }

    public void setTokenHeaderName(String tokenHeaderName) {
        this.tokenHeaderName = tokenHeaderName;
    }

    public String getExpirationDateHeaderName() {
        return expirationDateHeaderName;
    }

    public void setExpirationDateHeaderName(String expirationDateHeaderName) {
        this.expirationDateHeaderName = expirationDateHeaderName;
    }

    public Integer getExpirationDateAfterDays() {
        return expirationDateAfterDays;
    }

    public void setExpirationDateAfterDays(Integer expirationDateAfterDays) {
        this.expirationDateAfterDays = expirationDateAfterDays;
    }

    public SecretKey getSigningSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String getAuthorizationHeader(){
        return HttpHeaders.AUTHORIZATION;
    }

    public String getContentType(){
        return MediaType.APPLICATION_JSON_VALUE;
    }

    public String getCharacterEncoding() {
        return StandardCharsets.UTF_8.name();
    }
}
