package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.dto.HistoryDto;

import java.util.List;

public interface IHistoryService {

    List<HistoryDto> getAllHistoryByIdUser(String idUser);
}
