/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.model.Transaksi;

import java.math.BigDecimal;

public interface ITransaksiService {

    Transaksi insertTransaction(String transaksiId, String kategoriTransaksi, String userId, BigDecimal totalAmount);

    String getFormatIdTransaksi(String kategoriTransaksi);
}
