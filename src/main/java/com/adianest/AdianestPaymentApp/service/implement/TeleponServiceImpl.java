package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.dao.KategoriTeleponDao;
import com.adianest.AdianestPaymentApp.dao.TransaksiTeleponDao;
import com.adianest.AdianestPaymentApp.dto.TeleponDto;
import com.adianest.AdianestPaymentApp.model.*;
import com.adianest.AdianestPaymentApp.service.ISaldo;
import com.adianest.AdianestPaymentApp.service.ITeleponService;
import com.adianest.AdianestPaymentApp.service.ITransaksiService;
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

        transaksiService.insertTransaction(
                transaksiId,
                EnumKategoriTransaksi.TELEPON.name(),
                dto.getIdUser(),
                harga);

        Saldo newSaldo = saldoService.insertSaldo(dto.getIdUser(), harga.negate());

        return newSaldo != null;
    }
}
