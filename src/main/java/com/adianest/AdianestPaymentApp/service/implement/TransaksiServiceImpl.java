/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.dao.KategoriTransaksiDao;
import com.adianest.AdianestPaymentApp.dao.TransaksiDao;
import com.adianest.AdianestPaymentApp.model.KategoriTransaksi;
import com.adianest.AdianestPaymentApp.model.Transaksi;
import com.adianest.AdianestPaymentApp.service.ITransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TransaksiServiceImpl implements ITransaksiService {

    @Autowired
    private KategoriTransaksiDao kategoriTransaksiDao;

    @Autowired
    private TransaksiDao transaksiDao;

    @Override
    public Transaksi insertTransaction(String transaksiId, String kategoriTransaksi, String userId, BigDecimal totalAmount) {
        Transaksi transaksi = new Transaksi();
        transaksi.setId(transaksiId);
        transaksi.setKategori(kategoriTransaksi);
        transaksi.setTglTransaksi(Timestamp.valueOf(LocalDateTime.now()));
        transaksi.setUserId(userId);
        transaksi.setTotalTransaksi(totalAmount);

        return transaksiDao.save(transaksi);
    }

    @Override
    public String getFormatIdTransaksi(String kategoriTransaksi) {
        KategoriTransaksi kategori = kategoriTransaksiDao.findById(kategoriTransaksi).orElseThrow(NullPointerException::new);
        String prefix = kategori.getPrefix();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyHH");
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(dtf);

        Transaksi transaksi = transaksiDao.findTopByIdStartsWithOrderByIdDesc(prefix).orElse(null);

        String formatId;
        if (transaksi == null) {
            formatId = prefix + date + "001";
        } else {
            String idTransaksi = transaksi.getId();
            String dateTimePrevious = idTransaksi.substring(idTransaksi.length() - 11, idTransaksi.length() - 3);
            LocalDateTime previous = LocalDateTime.parse(dateTimePrevious, dtf);

            int row = 1;
            if (now.isEqual(previous)) {
                String lastRow = idTransaksi.substring(idTransaksi.length() - 3);
                row = Integer.valueOf(lastRow);
                row++;
            }

            formatId = prefix + date + String.format("%03d", row);

        }
        return formatId;
    }
}
