package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.dto.DigitalDto;
import com.adianest.AdianestPaymentApp.dto.TopUpDto;

import java.util.List;

public interface IDigitalService {

    List<DigitalDto> getAllKategori();

    boolean insertTransaksi(DigitalDto dto);

    DigitalDto getTransaksiByIdTransaksi(String idTransaksi);

    String getNamaKategori(String kategori);
}
