package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.common.AppCommonUtil;
import com.adianest.AdianestPaymentApp.dao.KategoriPulsaDao;
import com.adianest.AdianestPaymentApp.dao.TransaksiPulsaDao;
import com.adianest.AdianestPaymentApp.dto.PulsaDto;
import com.adianest.AdianestPaymentApp.model.*;
import com.adianest.AdianestPaymentApp.service.IPulsaService;
import com.adianest.AdianestPaymentApp.service.ISaldo;
import com.adianest.AdianestPaymentApp.service.ITransaksiService;
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

        transaksiService.insertTransaction(
                transaksiId,
                EnumKategoriTransaksi.PULSA_REGULER.name(),
                dto.getIdUser(),
                hargaPaket);

        Saldo newSaldo = saldoService.insertSaldo(dto.getIdUser(), hargaPaket.negate());

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
