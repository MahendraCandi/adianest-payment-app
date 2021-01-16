package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.dao.KategoriTeleponDao;
import com.adianest.AdianestPaymentApp.dao.TransaksiTeleponDao;
import com.adianest.AdianestPaymentApp.dto.TeleponDto;
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
public class TeleponServiceImpl implements ITeleponService {

    @Autowired
    KategoriTeleponDao kategoriTeleponDao;

    @Autowired
    private ITransaksiService transaksiService;

    @Autowired
    private TransaksiTeleponDao teleponDao;

    @Autowired
    private ISaldo saldoService;

    @Autowired
    private PushNotificationService push;

    @Autowired
    private IFcmService fcmService;

    @Autowired
    private INotifikasi notifikasiService;

    @Override
    public List<TeleponDto> getAllPaket() {
        List<TeleponDto> dtos = new ArrayList<>();
        List<KategoriTelepon> list = kategoriTeleponDao.findAll();
        for (KategoriTelepon k : list) {
            TeleponDto dto = new TeleponDto();
            dto.setIdPaket(k.getId());

            String deskripsi = String.format("%s Menit ke semua Operator dan %s Menit ke sesama Operator",
                    k.getBedaOperator(), k.getSesamaOperator());
            dto.setDeskripsiPaket(deskripsi);
            dto.setHarga(k.getHargaPaket().setScale(0, BigDecimal.ROUND_FLOOR).toString());

            dtos.add(dto);
        }

        return dtos;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertPaket(TeleponDto dto) {

        String transaksiId = transaksiService.getFormatIdTransaksi(EnumKategoriTransaksi.TELEPON.name());
        BigDecimal harga = new BigDecimal(dto.getHarga());

        TransaksiTelepon ts = new TransaksiTelepon();
        ts.setTransaksiId(transaksiId);
        ts.setPaketTelepon(dto.getIdPaket());
        ts.setNomorTujuan(dto.getNomorTujuan());
        ts.setHargaPaket(harga);

        teleponDao.save(ts);

        Transaksi t = transaksiService.insertTransaction(
                transaksiId,
                EnumKategoriTransaksi.TELEPON.name(),
                dto.getIdUser(),
                harga);

        Saldo newSaldo = saldoService.insertSaldo(dto.getIdUser(), harga.negate());

        KategoriTelepon k = kategoriTeleponDao.findById(ts.getPaketTelepon()).orElse(new KategoriTelepon());

        PushNotificationRequest req = new PushNotificationRequest();
        req.setTitle("Adianest Info");
        StringBuilder builder = new StringBuilder();
        builder.append("Terima kasih, pembelian paket ")
                .append(k.getSesamaOperator())
                .append(" MENIT ")
                .append(transaksiId)
                .append(" berhasil dilakukan");
        req.setMessage(builder.toString());
        req.setToken(fcmService.getToken(dto.getIdUser()));
        push.sendPushNotificationToToken(req);

        notifikasiService.insertNotifikasi(t.getUserId(), t.getId(), t.getKategori(), builder.toString(), t.getTglTransaksi());


        return newSaldo != null;
    }

    @Override
    public TeleponDto getTransaksiByIdTransaksi(String idTransaksi) {
        TransaksiTelepon t = teleponDao.findById(idTransaksi).orElse(null);
        TeleponDto dto = new TeleponDto();
        if (t != null) {
            KategoriTelepon k = kategoriTeleponDao.findById(t.getPaketTelepon()).orElse(new KategoriTelepon());
            dto.setTransaksiId(t.getTransaksiId());
            dto.setNomorTujuan(t.getNomorTujuan());
            dto.setIdPaket(t.getPaketTelepon());
            dto.setHarga(t.getHargaPaket().setScale(0, BigDecimal.ROUND_FLOOR).toString());
            dto.setNamaPaket(k.getSesamaOperator() + " MENIT");
        }
        return dto;
    }
}
