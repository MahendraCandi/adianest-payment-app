package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.dto.PulsaDto;

import java.util.List;

public interface IPulsaService {

    List<PulsaDto> getAllKategori();

    boolean insertTransaksi(PulsaDto dto);
}
