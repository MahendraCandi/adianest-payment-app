/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.dao.KategoriTopUpDao;
import com.adianest.AdianestPaymentApp.dao.TopUpDao;
import com.adianest.AdianestPaymentApp.dto.TopUpDto;
import com.adianest.AdianestPaymentApp.model.KategoriTopUp;
import com.adianest.AdianestPaymentApp.model.Saldo;
import com.adianest.AdianestPaymentApp.model.TransaksiTopup;
import com.adianest.AdianestPaymentApp.service.ISaldo;
import com.adianest.AdianestPaymentApp.service.ITopUpService;
import com.adianest.AdianestPaymentApp.service.ITransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TopUpServiceImpl implements ITopUpService {

    @Autowired
    private TopUpDao topUpDao;

    @Autowired
    private KategoriTopUpDao kategoriTopUpDao;

    @Autowired
    private ITransaksiService transaksiService;

    @Autowired
    private ISaldo saldoService;


    @Transactional( rollbackFor = Exception.class)
    @Override
    public boolean insertTopUp(TopUpDto dto) {

        if (dto.isKonfirmasiTransaksi()) {
            TransaksiTopup topup = new TransaksiTopup();
            topup.setTransaksiId(dto.getTransaksiId());
            topup.setKategori(dto.getKategoriTopUp());
            topup.setKodePembayaran(dto.getKodePembayaran());
            topup.setNominal(new BigDecimal(dto.getNominalTopUp()));

            topUpDao.save(topup);

            transaksiService.insertTransaction(dto.getTransaksiId(), dto.getKategoriTransaksi(), dto.getUserId(),
                    new BigDecimal(dto.getTotalAmount()).setScale(2, BigDecimal.ROUND_CEILING));

            Saldo newSaldo = saldoService.insertSaldo(dto.getUserId(),
                    new BigDecimal(dto.getNominalTopUp()).setScale(2, BigDecimal.ROUND_CEILING));

            return newSaldo.getId() != null;

        }

        return false;
    }

    @Override
    public TopUpDto confirmTopUp(TopUpDto dto) {

        String transaksiId = transaksiService.getFormatIdTransaksi(dto.getKategoriTransaksi());
        String paymentCode = generatePaymentCode(dto.getKategoriTopUp(), dto.getNoTelpon());
        BigDecimal nominalTopUp = new BigDecimal(dto.getNominalTopUp()).setScale(2, BigDecimal.ROUND_CEILING);

        dto.setTransaksiId(transaksiId);
        dto.setKodePembayaran(paymentCode);
        dto.setNominalTopUp(nominalTopUp.toString());
        dto.setTotalAmount(nominalTopUp.toString());
        dto.setKonfirmasiTransaksi(false);

        return dto;

    }

    private String generatePaymentCode(String kategoriTopUpStr, String userPhone) {
        KategoriTopUp kategoriTopUp = kategoriTopUpDao.findById(kategoriTopUpStr).orElseThrow(NullPointerException::new);

        String prefix = kategoriTopUp.getPrefix();
        userPhone = userPhone.substring(1);

        return prefix + userPhone;
    }
}
