/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.dto.SaldoDto;
import com.adianest.AdianestPaymentApp.model.Saldo;

import java.math.BigDecimal;

public interface ISaldo {

    Saldo insertSaldo(String userId, BigDecimal mutationValue);

    Saldo insertFirstSaldo(String userId);

    Saldo getEndingBalanceByUserId(String userId);

    SaldoDto getEndingBalanceByUserIdAsDto(String userId);

}
