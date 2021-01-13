package com.adianest.AdianestPaymentApp.service;

public interface IFcmService {

    boolean insertToken(String id, String token);

    String getToken(String id);
}
