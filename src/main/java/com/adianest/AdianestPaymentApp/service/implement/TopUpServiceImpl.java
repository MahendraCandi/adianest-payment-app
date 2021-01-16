/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.common.AppCommonUtil;
import com.adianest.AdianestPaymentApp.dao.KategoriTopUpDao;
import com.adianest.AdianestPaymentApp.dao.TopUpDao;
import com.adianest.AdianestPaymentApp.dao.TransaksiTopUpConfirmDao;
import com.adianest.AdianestPaymentApp.dto.TopUpDto;
import com.adianest.AdianestPaymentApp.fcm.PushNotificationRequest;
import com.adianest.AdianestPaymentApp.fcm.PushNotificationService;
import com.adianest.AdianestPaymentApp.model.*;
import com.adianest.AdianestPaymentApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TopUpServiceImpl implements ITopUpService {

    @Autowired
    private TopUpDao topUpDao;

    @Autowired
    private TransaksiTopUpConfirmDao topUpConfirmDao;

    @Autowired
    private KategoriTopUpDao kategoriTopUpDao;

    @Autowired
    private ITransaksiService transaksiService;

    @Autowired
    private ISaldo saldoService;

    @Autowired
    private PushNotificationService push;

    @Autowired
    private IFcmService fcmService;

    @Autowired
    private INotifikasi notifikasiService;

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

            Transaksi t = transaksiService.insertTransaction(dto.getTransaksiId(), dto.getKategoriTransaksi(), dto.getUserId(),
                    new BigDecimal(dto.getTotalAmount()).setScale(2, BigDecimal.ROUND_FLOOR));

            Saldo newSaldo = saldoService.insertSaldo(dto.getUserId(),
                    new BigDecimal(dto.getNominalTopUp()).setScale(2, BigDecimal.ROUND_FLOOR));

            // update top up confirm
            TransaksiTopupConfirmPK pk = new TransaksiTopupConfirmPK();
            pk.setUserId(dto.getUserId());
            pk.setTransaksiId(dto.getTransaksiId());
            pk.setKodePembayaran(dto.getKodePembayaran());

            TransaksiTopupConfirm topupConfirm = topUpConfirmDao.findById(pk).orElseThrow(NullPointerException::new);
            topupConfirm.setKonfirmasiTransaksi(true);
            topUpConfirmDao.save(topupConfirm);

            PushNotificationRequest req = new PushNotificationRequest();
            req.setTitle("Adianest Info");
            StringBuilder builder = new StringBuilder();
            builder.append("Terima kasih, top up melalui ")
                    .append(AppCommonUtil.firstCharacterToUpperCase(dto.getKategoriTopUp()))
                    .append(" sebesar ")
                    .append(AppCommonUtil.toRupiahFormat(dto.getNominalTopUp()))
                    .append(" berhasil dilakukan");
            req.setMessage(builder.toString());
            req.setToken(fcmService.getToken(dto.getUserId()));
            push.sendPushNotificationToToken(req);

            notifikasiService.insertNotifikasi(t.getUserId(), t.getId(), t.getKategori(), builder.toString(), t.getTglTransaksi());


            return newSaldo.getId() != null;

        }

        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TopUpDto insertConfirmTopUp(TopUpDto dto) {

        String transaksiId = transaksiService.getFormatIdTransaksi(dto.getKategoriTransaksi());
        String paymentCode = generatePaymentCode(dto.getKategoriTopUp(), dto.getNoTelpon());
        BigDecimal nominalTopUp = new BigDecimal(dto.getNominalTopUp()).setScale(2, BigDecimal.ROUND_CEILING);

        dto.setTransaksiId(transaksiId);
        dto.setKodePembayaran(paymentCode);
        dto.setNominalTopUp(nominalTopUp.toString());
        dto.setTotalAmount(nominalTopUp.toString());
        dto.setKonfirmasiTransaksi(false);

        TransaksiTopupConfirm topupConfirm = new TransaksiTopupConfirm();
        topupConfirm.setTransaksiId(dto.getTransaksiId());
        topupConfirm.setKodePembayaran(dto.getKodePembayaran());
        topupConfirm.setNominalTopup(nominalTopUp);
        topupConfirm.setTotalAmount(nominalTopUp);
        topupConfirm.setKonfirmasiTransaksi(false);
        topupConfirm.setUserId(dto.getUserId());
        topupConfirm.setKategoriTopUp(dto.getKategoriTopUp());
        topupConfirm.setKategoriTransaksi(dto.getKategoriTransaksi());
        topupConfirm.setNoTelpon(dto.getNoTelpon());

        topUpConfirmDao.save(topupConfirm);
        
        return dto;

    }

    @Override
    public TopUpDto getConfirmTopUp(TopUpDto dto) {

        TransaksiTopupConfirmPK pk = new TransaksiTopupConfirmPK();
        pk.setUserId(dto.getUserId());
        pk.setTransaksiId(dto.getTransaksiId());
        pk.setKodePembayaran(dto.getKodePembayaran());

        TransaksiTopupConfirm topupConfirm = topUpConfirmDao.findById(pk).orElseThrow(NullPointerException::new);

        dto.setTransaksiId(dto.getTransaksiId());
        dto.setKodePembayaran(topupConfirm.getKodePembayaran());
        dto.setNominalTopUp(topupConfirm.getNominalTopup().toString());
        dto.setTotalAmount(topupConfirm.getTotalAmount().toString());
        dto.setKonfirmasiTransaksi(topupConfirm.getKonfirmasiTransaksi());
        dto.setUserId(topupConfirm.getUserId());
        dto.setKategoriTopUp(topupConfirm.getKategoriTopUp());
        dto.setKategoriTransaksi(topupConfirm.getKategoriTransaksi());
        dto.setNoTelpon(topupConfirm.getNoTelpon());
        
        return dto;
        
    }

    @Override
    public TopUpDto getTransaksiByIdTransaksi(String idTransaksi) {
        TransaksiTopup topup = topUpDao.findById(idTransaksi).orElse(null);
        TopUpDto dto = new TopUpDto();
        if (topup != null) {
            dto.setTransaksiId(topup.getTransaksiId());
            dto.setKategoriTopUp(topup.getKategori());
            dto.setKodePembayaran(topup.getKodePembayaran());
            dto.setNominalTopUp(topup.getNominal().setScale(0, BigDecimal.ROUND_FLOOR).toString());
        }
        return dto;
    }

    private String generatePaymentCode(String kategoriTopUpStr, String userPhone) {
        KategoriTopUp kategoriTopUp = kategoriTopUpDao.findById(kategoriTopUpStr).orElseThrow(NullPointerException::new);

        String prefix = kategoriTopUp.getPrefix();
        userPhone = userPhone.substring(1);

        return prefix + userPhone;
    }
}
