package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.dao.KategoriSmsDao;
import com.adianest.AdianestPaymentApp.dao.TransaksiSmsDao;
import com.adianest.AdianestPaymentApp.dto.SmsDto;
import com.adianest.AdianestPaymentApp.fcm.PushNotificationRequest;
import com.adianest.AdianestPaymentApp.fcm.PushNotificationService;
import com.adianest.AdianestPaymentApp.model.*;
import com.adianest.AdianestPaymentApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SmsServiceImpl implements ISmsService {

    @Autowired
    private KategoriSmsDao kategoriSmsDao;

    @Autowired
    private ITransaksiService transaksiService;

    @Autowired
    private TransaksiSmsDao smsDao;

    @Autowired
    private ISaldo saldoService;

    @Autowired
    private PushNotificationService push;

    @Autowired
    private IFcmService fcmService;

    @Autowired
    private INotifikasi notifikasiService;

    @Override
    public List<SmsDto> getAllPacket() {
        List<KategoriSms> list = kategoriSmsDao.findAll();
        List<SmsDto> dtos = new ArrayList<>();
        for (KategoriSms ks : list) {
            SmsDto dto = new SmsDto();
            dto.setIdPaket(ks.getId());

            String deskripsi = String.format("%s SMS ke semua Operator dan %s SMS ke sesama Operator",
                    ks.getBedaOperator(), ks.getSamaOperator());
            dto.setDeskripsiPaket(deskripsi);
            dto.setHarga(ks.getHarga().setScale(0, BigDecimal.ROUND_FLOOR).toString());

            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertPaket(SmsDto dto) {
        String transaksiId = transaksiService.getFormatIdTransaksi(EnumKategoriTransaksi.SMS.name());
        BigDecimal harga = new BigDecimal(dto.getHarga());

        TransaksiSms ts = new TransaksiSms();
        ts.setTransaksiId(transaksiId);
        ts.setPaketSms(dto.getIdPaket());
        ts.setNomorTujuan(dto.getNomorTujuan());
        ts.setHargaPaket(harga);

        smsDao.save(ts);

        Transaksi t = transaksiService.insertTransaction(
                transaksiId,
                EnumKategoriTransaksi.SMS.name(),
                dto.getIdUser(),
                harga);

        Saldo newSaldo = saldoService.insertSaldo(dto.getIdUser(), harga.negate());

        KategoriSms k = kategoriSmsDao.findById(ts.getPaketSms()).orElse(new KategoriSms());

        PushNotificationRequest req = new PushNotificationRequest();
        req.setTitle("Adianest Info");
        StringBuilder builder = new StringBuilder();
        builder.append("Terima kasih, pembelian paket ")
                .append(k.getSamaOperator())
                .append(" SMS ")
                .append(transaksiId)
                .append(" berhasil dilakukan");
        req.setMessage(builder.toString());
        req.setToken(fcmService.getToken(dto.getIdUser()));
        push.sendPushNotificationToToken(req);

        notifikasiService.insertNotifikasi(t.getUserId(), t.getId(), t.getKategori(), builder.toString(), t.getTglTransaksi());

        return newSaldo != null;
    }

    @Override
    public SmsDto getTransaksiByIdTransaksi(String idTransaksi) {
        TransaksiSms sms = smsDao.findById(idTransaksi).orElse(null);
        SmsDto dto = new SmsDto();
        if (sms != null) {
            KategoriSms k = kategoriSmsDao.findById(sms.getPaketSms()).orElse(new KategoriSms());
            dto.setTransaksiId(sms.getTransaksiId());
            dto.setNomorTujuan(sms.getNomorTujuan());
            dto.setIdPaket(sms.getPaketSms());
            dto.setNamaPaket(k.getSamaOperator() + " SMS");
            dto.setHarga(sms.getHargaPaket().setScale(0, BigDecimal.ROUND_FLOOR).toString());
        }
        return dto;
    }
}
