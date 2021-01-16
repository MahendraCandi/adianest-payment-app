/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.dao.KategoriInternetDao;
import com.adianest.AdianestPaymentApp.dao.TransaksiPaketInternetDao;
import com.adianest.AdianestPaymentApp.dto.InternetDto;
import com.adianest.AdianestPaymentApp.fcm.PushNotificationRequest;
import com.adianest.AdianestPaymentApp.fcm.PushNotificationService;
import com.adianest.AdianestPaymentApp.model.*;
import com.adianest.AdianestPaymentApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class InternetServiceImpl implements IInternetService {

    @Autowired
    KategoriInternetDao kategoriInternetDao;

    @Autowired
    TransaksiPaketInternetDao internetDao;

    @Autowired
    ITransaksiService transaksiService;

    @Autowired
    PushNotificationService push;

    @Autowired
    IFcmService fcmService;

    @Autowired
    ISaldo saldoService;

    @Autowired
    INotifikasi notifikasiService;

    @Override
    public List<InternetDto> getAllPaketInternet() {
        List<KategoriInternet> kategoriInternet = kategoriInternetDao.findAll();
        List<InternetDto> dtos = new ArrayList<>();

        for (KategoriInternet k : kategoriInternet) {
            InternetDto dto = new InternetDto();
            dto.setIdKategori(k.getId());
            dto.setNamaPaket(k.getNama());
            dto.setJumlah(k.getJumlah().toString());
            dto.setUkuranSatuan(k.getSatuan());
            dto.setHargaPaket(k.getHarga().setScale(0, BigDecimal.ROUND_FLOOR).toString());
            dtos.add(dto);
        }
        dtos.sort(Comparator.comparing(((a) -> Long.parseLong(a.getJumlah()))));
        return dtos;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertTransaksi(InternetDto dto) {
        String transaksiId = transaksiService.getFormatIdTransaksi(EnumKategoriTransaksi.PAKET_INTERNET.name());
        BigDecimal hargaPaket = new BigDecimal(dto.getHargaPaket()).setScale(2, BigDecimal.ROUND_FLOOR);

        TransaksiPaketInternet internet = new TransaksiPaketInternet();
        internet.setPaketKuota(dto.getIdPaket());
        internet.setHargaKuota(hargaPaket);
        internet.setNomorTujuan(dto.getNomorTujuan());
        internet.setTransaksiId(transaksiId);

        internetDao.save(internet);

        Transaksi t = transaksiService.insertTransaction(
                transaksiId,
                EnumKategoriTransaksi.PAKET_INTERNET.name(),
                dto.getIdUser(),
                hargaPaket);

        Saldo newSaldo = saldoService.insertSaldo(dto.getIdUser(), hargaPaket.negate());

        KategoriInternet k = kategoriInternetDao.findById(internet.getPaketKuota()).orElse(new KategoriInternet());

        PushNotificationRequest req = new PushNotificationRequest();
        req.setTitle("Adianest Info");
        StringBuilder builder = new StringBuilder();
        builder.append("Terima kasih, pembelian paket data ")
                .append(k.getNama() == null ? "-" : k.getNama())
                .append(" ")
                .append(transaksiId)
                .append(" berhasil dilakukan");
        req.setMessage(builder.toString());
        req.setToken(fcmService.getToken(dto.getIdUser()));
        push.sendPushNotificationToToken(req);

        notifikasiService.insertNotifikasi(t.getUserId(), t.getId(), t.getKategori(), builder.toString(), t.getTglTransaksi());

        return newSaldo.getId() != null;
    }

    @Override
    public InternetDto getTransaksiByIdTransaksi(String idTransaksi) {
        TransaksiPaketInternet internet = internetDao.findById(idTransaksi).orElse(null);
        InternetDto dto = null;
        if (internet != null) {
            KategoriInternet k = kategoriInternetDao.findById(internet.getPaketKuota()).orElse(null);
            dto = new InternetDto();
            dto.setIdTransaksi(internet.getTransaksiId());
            dto.setNomorTujuan(internet.getNomorTujuan());
            dto.setNamaPaket(k != null ? k.getNama() : null);
            dto.setHargaPaket(internet.getHargaKuota().setScale(0, BigDecimal.ROUND_FLOOR).toString());
        }
        return dto;
    }
}
