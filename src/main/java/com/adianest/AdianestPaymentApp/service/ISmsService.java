package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.dto.SmsDto;
import com.adianest.AdianestPaymentApp.dto.TopUpDto;

import java.util.List;

public interface ISmsService {

    List<SmsDto> getAllPacket();

    boolean insertPaket(SmsDto dto);

    SmsDto getTransaksiByIdTransaksi(String idTransaksi);
}
