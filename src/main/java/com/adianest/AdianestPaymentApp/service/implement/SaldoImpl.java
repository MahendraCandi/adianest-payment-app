/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.dao.SaldoDao;
import com.adianest.AdianestPaymentApp.dto.SaldoDto;
import com.adianest.AdianestPaymentApp.model.Saldo;
import com.adianest.AdianestPaymentApp.service.ISaldo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class SaldoImpl implements ISaldo {

    @Autowired
    private SaldoDao saldoDao;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Saldo insertSaldo(String userId, BigDecimal mutationValue) {
        Saldo currentSaldo = getEndingBalanceByUserId(userId);
        Saldo newSaldo = new Saldo();

        newSaldo.setUserId(userId);
        newSaldo.setSaldoAwal(currentSaldo.getSaldoAkhir());
        newSaldo.setNilaiMutasi(mutationValue);
        newSaldo.setSaldoAkhir(currentSaldo.getSaldoAkhir().add(mutationValue));
        newSaldo.setTglMutasi(Timestamp.valueOf(LocalDateTime.now()));

        newSaldo = saldoDao.save(newSaldo);

        return newSaldo;
    }

    @Override
    public Saldo getEndingBalanceByUserId(String userId) {
        return saldoDao.findTopByUserIdOrderByTglMutasiDesc(userId).orElseThrow(NullPointerException::new);
    }

    @Override
    public SaldoDto getEndingBalanceByUserIdAsDto(String userId) {
        Saldo saldo = saldoDao.findTopByUserIdOrderByTglMutasiDesc(userId).orElseThrow(NullPointerException::new);
        SaldoDto dto = new SaldoDto();
        dto.setUserId(saldo.getUserId());
        dto.setEndingBalance(saldo.getSaldoAkhir().toString());
        return dto;
    }
}
