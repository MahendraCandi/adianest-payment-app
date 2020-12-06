package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.dto.SmsDto;

import java.util.List;

public interface ISmsService {

    List<SmsDto> getAllPacket();

    boolean insertPaket(SmsDto dto);
}
