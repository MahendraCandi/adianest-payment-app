/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.model.Saldo;

import java.math.BigDecimal;

public interface ISaldo {

    Saldo insertSaldo(String userId, BigDecimal mutationValue);

    Saldo getEndingBalanceByUserId(String userId);

}
