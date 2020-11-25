package com.adianest.AdianestPaymentApp.jwt;

import java.util.Date;

public class JwtResponseBody {

    private String token;
    private Date expirationDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
