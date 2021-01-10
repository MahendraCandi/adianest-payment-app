package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.common.AppCommonUtil;
import com.adianest.AdianestPaymentApp.dao.KategoriDigitalDao;
import com.adianest.AdianestPaymentApp.dao.TransaksiDigitalDao;
import com.adianest.AdianestPaymentApp.dto.DigitalDto;
import com.adianest.AdianestPaymentApp.model.EnumKategoriTransaksi;
import com.adianest.AdianestPaymentApp.model.KategoriDigitial;
import com.adianest.AdianestPaymentApp.model.Saldo;
import com.adianest.AdianestPaymentApp.model.TransaksiDigital;
import com.adianest.AdianestPaymentApp.service.IDigitalService;
import com.adianest.AdianestPaymentApp.service.ISaldo;
import com.adianest.AdianestPaymentApp.service.ITransaksiService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DigitalServiceImpl implements IDigitalService {

    @Autowired
    KategoriDigitalDao kategoriDigitalDao;

    @Autowired
    TransaksiDigitalDao digitalDao;

    @Autowired
    ITransaksiService transaksiService;

    @Autowired
    ISaldo saldoService;

    @Override
    public List<DigitalDto> getAllKategori() {
        List<KategoriDigitial> kategoriDigitials = kategoriDigitalDao.findAll();
        List<DigitalDto> dtos = new ArrayList<>();

        for (KategoriDigitial k : kategoriDigitials) {
            DigitalDto dto = new DigitalDto();
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
    public boolean insertTransaksi(DigitalDto dto) {
        String transaksiId;
        String kategori;
        BigDecimal nominalTransfer = new BigDecimal(dto.getNominalTransfer()).setScale(2, BigDecimal.ROUND_FLOOR);

        TransaksiDigital td = new TransaksiDigital();

        if (dto.getIdKategori().equalsIgnoreCase(EnumKategoriTransaksi.OVO.name())) {
            kategori = EnumKategoriTransaksi.OVO.name();
            transaksiId = transaksiService.getFormatIdTransaksi(EnumKategoriTransaksi.OVO.name());
        } else if (dto.getIdKategori().equalsIgnoreCase(EnumKategoriTransaksi.SHOOPE_PAY.name())) {
            kategori = EnumKategoriTransaksi.SHOOPE_PAY.name();
            transaksiId = transaksiService.getFormatIdTransaksi(EnumKategoriTransaksi.SHOOPE_PAY.name());
        } else if (dto.getIdKategori().equalsIgnoreCase(EnumKategoriTransaksi.DANA.name())) {
            kategori = EnumKategoriTransaksi.DANA.name();
            transaksiId = transaksiService.getFormatIdTransaksi(EnumKategoriTransaksi.DANA.name());
        } else if (dto.getIdKategori().equalsIgnoreCase(EnumKategoriTransaksi.GOPAY.name())) {
            kategori = EnumKategoriTransaksi.GOPAY.name();
            transaksiId = transaksiService.getFormatIdTransaksi(EnumKategoriTransaksi.GOPAY.name());
        } else if (dto.getIdKategori().equalsIgnoreCase(EnumKategoriTransaksi.GRAB.name())) {
            kategori = EnumKategoriTransaksi.GRAB.name();
            transaksiId = transaksiService.getFormatIdTransaksi(EnumKategoriTransaksi.GRAB.name());
        } else {
            throw new IllegalStateException("Kategori digital tidak ditemukan");
        }

        td.setKategoriId(kategori);
        td.setTransaksiId(transaksiId);
        td.setNominalTransfer(nominalTransfer);
        td.setNomorTujuan(dto.getNomorTujuan());

        digitalDao.save(td);

        transaksiService.insertTransaction(
                transaksiId,
                kategori,
                dto.getIdUser(),
                nominalTransfer);

        Saldo newSaldo = saldoService.insertSaldo(dto.getIdUser(), nominalTransfer.negate());

        return newSaldo.getId() != null;
    }
}