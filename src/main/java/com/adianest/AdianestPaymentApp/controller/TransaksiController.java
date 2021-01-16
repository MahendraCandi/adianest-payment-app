package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.common.AppCommonUtil;
import com.adianest.AdianestPaymentApp.dto.*;
import com.adianest.AdianestPaymentApp.model.EnumKategoriTransaksi;
import com.adianest.AdianestPaymentApp.model.Transaksi;
import com.adianest.AdianestPaymentApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {

    @Autowired
    ITransaksiService transaksiService;

    @Autowired
    IDigitalService digitalService;

    @Autowired
    ITopUpService topUpService;

    @Autowired
    IInternetService internetService;

    @Autowired
    IPulsaService pulsaService;

    @Autowired
    ISmsService smsService;

    @Autowired
    ITeleponService teleponService;

    @Autowired
    IUserService userService;

    @PostMapping("/detail")
    public TransaksiDto getDetailTransaksiByIdTransaksi(@RequestParam("id") String idTransaksi) {
        Transaksi t = transaksiService.getTransaksiByIdTransaksi(idTransaksi);
        TransaksiDto dto = new TransaksiDto();

        dto.setNoTransaksi(t.getId());
        dto.setUserId(t.getUserId());
        dto.setHarga(AppCommonUtil
                .toRupiahFormat(t.getTotalTransaksi().setScale(0, BigDecimal.ROUND_FLOOR).toString()));
        LocalDateTime dateTime = t.getTglTransaksi().toLocalDateTime();
        dto.setTanggal(dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag("id-ID"))));
        dto.setWaktu(dateTime.format(DateTimeFormatter.ofPattern("HH:mm")));

        if (t.getKategori().equals(EnumKategoriTransaksi.GOPAY.name()) ||
                t.getKategori().equals(EnumKategoriTransaksi.DANA.name()) ||
                t.getKategori().equals(EnumKategoriTransaksi.GRAB.name()) ||
                t.getKategori().equals(EnumKategoriTransaksi.OVO.name()) ||
                t.getKategori().equals(EnumKategoriTransaksi.SHOOPE_PAY.name())) {
            DigitalDto digitalDto = digitalService.getTransaksiByIdTransaksi(idTransaksi);
            dto.setNoTujuan(digitalDto.getNomorTujuan());
            dto.setNominal(AppCommonUtil.toRupiahFormat(digitalDto.getNominalTransfer()));
            dto.setJenis("Saldo " + digitalService.getNamaKategori(digitalDto.getIdKategori()));
        } else if (t.getKategori().equals(EnumKategoriTransaksi.TOP_UP.name())) {
            TopUpDto topUpDto = topUpService.getTransaksiByIdTransaksi(t.getId());
            UserDto userDto = userService.getOneUserById(t.getUserId());
            dto.setNoTujuan(userDto.getNoTelpon());
            dto.setNominal(AppCommonUtil.toRupiahFormat(topUpDto.getNominalTopUp()));
            dto.setJenis("Top Up Saldo " + topUpDto.getKategoriTopUp());
        } else if (t.getKategori().equals(EnumKategoriTransaksi.PAKET_INTERNET.name())) {
            InternetDto internetDto = internetService.getTransaksiByIdTransaksi(t.getId());
            dto.setNoTujuan(internetDto.getNomorTujuan());
            dto.setNominal(AppCommonUtil.toRupiahFormat(internetDto.getHargaPaket()));
            dto.setJenis("Kuota Data");
        } else if (t.getKategori().equals(EnumKategoriTransaksi.PULSA_REGULER.name())) {
            PulsaDto pulsaDto = pulsaService.getTransaksiByIdTransaksi(t.getId());
            dto.setNoTujuan(pulsaDto.getNomorTujuan());
            dto.setNominal(AppCommonUtil.toRupiahFormat(pulsaDto.getJumlah()));
            dto.setJenis("Pulsa Reguler");
        } else if (t.getKategori().equals(EnumKategoriTransaksi.SMS.name())) {
            SmsDto smsDto = smsService.getTransaksiByIdTransaksi(t.getId());
            dto.setNoTujuan(smsDto.getNomorTujuan());
            dto.setNominal(AppCommonUtil.toRupiahFormat(smsDto.getHarga()));
            dto.setJenis("Paket SMS");
        } else if (t.getKategori().equals(EnumKategoriTransaksi.TELEPON.name())) {
            TeleponDto teleponDto = teleponService.getTransaksiByIdTransaksi(t.getId());
            dto.setNoTujuan(teleponDto.getNomorTujuan());
            dto.setNominal(AppCommonUtil.toRupiahFormat(teleponDto.getHarga()));
            dto.setJenis("Paket Telepon");
        }

        return dto;
    }
}
