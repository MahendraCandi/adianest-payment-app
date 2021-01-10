package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.dto.DigitalDto;

import java.util.List;

public interface IDigitalService {

    List<DigitalDto> getAllKategori();

    boolean insertTransaksi(DigitalDto dto);
}
