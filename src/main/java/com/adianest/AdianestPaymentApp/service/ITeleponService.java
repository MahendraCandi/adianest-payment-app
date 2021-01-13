package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.dto.TeleponDto;
import com.adianest.AdianestPaymentApp.dto.TopUpDto;

import java.util.List;

public interface ITeleponService {

    List<TeleponDto> getAllPaket();

    boolean insertPaket(TeleponDto dto);

    TeleponDto getTransaksiByIdTransaksi(String idTransaksi);
}
