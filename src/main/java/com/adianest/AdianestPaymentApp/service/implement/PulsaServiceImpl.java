package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.common.AppCommonUtil;
import com.adianest.AdianestPaymentApp.dao.KategoriPulsaDao;
import com.adianest.AdianestPaymentApp.dao.TransaksiPulsaDao;
import com.adianest.AdianestPaymentApp.dto.PulsaDto;
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
public class PulsaServiceImpl implements IPulsaService {

    @Autowired
    private TransaksiPulsaDao transaksiPulsaDao;

    @Autowired
    private KategoriPulsaDao kategoriPulsaDao;

    @Autowired
    ITransaksiService transaksiService;

    @Autowired
    ISaldo saldoService;

    @Autowired
    PushNotificationService push;

    @Autowired
    IFcmService fcmService;

    @Autowired
    INotifikasi notifikasiService;

    @Override
    public List<PulsaDto> getAllKategori() {
        List<KategoriPulsa> kategoriPulsas = kategoriPulsaDao.findAll();
        List<PulsaDto> dtos = new ArrayList<>();

        for (KategoriPulsa k : kategoriPulsas) {
            PulsaDto dto = new PulsaDto();
            dto.setIdPaket(k.getId());
            dto.setJumlah(AppCommonUtil.toRupiahFormat(k.getJumlah()));
            dto.setHargaPaket(k.getHarga().setScale(0, BigDecimal.ROUND_FLOOR).toString());

            dtos.add(dto);
        }

        dtos.sort(Comparator.comparing(((a) -> Long.parseLong(a.getHargaPaket()))));

        return dtos;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertTransaksi(PulsaDto dto) {
        String transaksiId = transaksiService.getFormatIdTransaksi(EnumKategoriTransaksi.PULSA_REGULER.name());
        BigDecimal hargaPaket = new BigDecimal(dto.getHargaPaket()).setScale(2, BigDecimal.ROUND_FLOOR);

        TransaksiPulsa tp = new TransaksiPulsa();
        tp.setTransaksiId(transaksiId);
        tp.setNomorDari(dto.getNomorDari());
        tp.setNomorTujuan(dto.getNomorTujuan());
        tp.setJumlahPulsa(hargaPaket);

        transaksiPulsaDao.save(tp);

        Transaksi t = transaksiService.insertTransaction(
                transaksiId,
                EnumKategoriTransaksi.PULSA_REGULER.name(),
                dto.getIdUser(),
                hargaPaket);

        Saldo newSaldo = saldoService.insertSaldo(dto.getIdUser(), hargaPaket.negate());

        PushNotificationRequest req = new PushNotificationRequest();
        req.setTitle("Adianest Info");
        StringBuilder builder = new StringBuilder();
        builder.append("Terima kasih, pembelian paket pulsa ")
                .append(dto.getIdPaket())
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
    public PulsaDto getTransaksiByIdTransaksi(String idTransaksi) {
        TransaksiPulsa tp = transaksiPulsaDao.findById(idTransaksi).orElse(null);
        PulsaDto dto = new PulsaDto();
        if (tp != null) {
            dto.setIdTransaksi(tp.getTransaksiId());
            dto.setNomorDari(tp.getNomorDari());
            dto.setNomorTujuan(tp.getNomorTujuan());
            dto.setJumlah(tp.getJumlahPulsa().setScale(0, BigDecimal.ROUND_FLOOR).toString());
        }
        return dto;
    }
}
