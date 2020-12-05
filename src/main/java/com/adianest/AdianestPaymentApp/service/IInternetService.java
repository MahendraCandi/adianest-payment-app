/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.dto.InternetDto;

import java.util.List;

public interface IInternetService {

    List<InternetDto> getAllPaketInternet();

    boolean insertTransaksi(InternetDto dto);

    InternetDto getTransaksiByIdTransaksi(String idTransaksi);
}
