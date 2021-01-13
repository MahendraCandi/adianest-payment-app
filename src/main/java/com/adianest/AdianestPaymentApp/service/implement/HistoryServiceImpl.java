package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.common.AppCommonUtil;
import com.adianest.AdianestPaymentApp.dto.*;
import com.adianest.AdianestPaymentApp.model.EnumKategoriTransaksi;
import com.adianest.AdianestPaymentApp.model.Transaksi;
import com.adianest.AdianestPaymentApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class HistoryServiceImpl implements IHistoryService {

    @Autowired
    ITransaksiService transaksiService;

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
    IDigitalService digitalService;

    @SuppressWarnings("unchecked")
    @Override
    public List<HistoryDto> getAllHistoryByIdUser(String idUser) {
        List<HistoryDto> dtos = new ArrayList<>();
        List<Transaksi> transaksiList = transaksiService.getAllTransaksiByIdUser(idUser);

        if (transaksiList != null && !transaksiList.isEmpty()) {
            for (Transaksi t : transaksiList) {
                if (t.getKategori().equals(EnumKategoriTransaksi.TOP_UP.name())) {
                    TopUpDto topUpDto = topUpService.getTransaksiByIdTransaksi(t.getId());
                    HistoryDto<TopUpDto> dto = new HistoryDto<>();
                    dto.setDetailTransaksi(topUpDto);
                    setTransaksi(dto, t);

                    String deskripsi = String.format("Top up saldo melalui %s sebesar Rp. %s",
                            AppCommonUtil.firstCharacterToUpperCase(topUpDto.getKategoriTopUp()),
                            AppCommonUtil.toRupiahFormat(dto.getTotalTransaksi()));
                    dto.setDeskripsi(deskripsi);
                    dtos.add(dto);
                } else if (t.getKategori().equals(EnumKategoriTransaksi.PAKET_INTERNET.name())) {
                    InternetDto internetDto = internetService.getTransaksiByIdTransaksi(t.getId());
                    HistoryDto<InternetDto> dto = new HistoryDto<>();
                    dto.setDetailTransaksi(internetDto);
                    setTransaksi(dto, t);

                    String deskripsi = String
                            .format("Pembelian paket data %s ke no. %s berhasil, harga Rp. %s",
                                    internetDto.getNamaPaket(), internetDto.getNomorTujuan(),
                                    AppCommonUtil.toRupiahFormat(dto.getTotalTransaksi()));
                    dto.setDeskripsi(deskripsi);
                    dtos.add(dto);
                } else if (t.getKategori().equals(EnumKategoriTransaksi.PULSA_REGULER.name())) {
                    PulsaDto pulsaDto = pulsaService.getTransaksiByIdTransaksi(t.getId());
                    HistoryDto<PulsaDto> dto = new HistoryDto<>();
                    dto.setDetailTransaksi(pulsaDto);
                    setTransaksi(dto, t);

                    String deskripsi = String
                            .format("Pembelian paket pulsa %s ke no. %s berhasil, harga Rp. %s",
                                    pulsaDto.getJumlah(), pulsaDto.getNomorTujuan(),
                                    AppCommonUtil.toRupiahFormat(dto.getTotalTransaksi()));
                    dto.setDeskripsi(deskripsi);
                    dtos.add(dto);
                } else if (t.getKategori().equals(EnumKategoriTransaksi.SMS.name())) {
                    SmsDto smsDto = smsService.getTransaksiByIdTransaksi(t.getId());
                    HistoryDto<SmsDto> dto = new HistoryDto<>();
                    dto.setDetailTransaksi(smsDto);
                    setTransaksi(dto, t);

                    String deskripsi = String
                            .format("Pembelian paket %s ke no. %s berhasil, harga Rp. %s",
                                    smsDto.getNamaPaket(), smsDto.getNomorTujuan(),
                                    AppCommonUtil.toRupiahFormat(dto.getTotalTransaksi()));
                    dto.setDeskripsi(deskripsi);
                    dtos.add(dto);
                } else if (t.getKategori().equals(EnumKategoriTransaksi.TELEPON.name())) {
                    TeleponDto teleponDto = teleponService.getTransaksiByIdTransaksi(t.getId());
                    HistoryDto<TeleponDto> dto = new HistoryDto<>();
                    dto.setDetailTransaksi(teleponDto);
                    setTransaksi(dto, t);

                    String deskripsi = String
                            .format("Pembelian paket data %s ke no. %s berhasil, harga Rp. %s",
                                    teleponDto.getNamaPaket(), teleponDto.getNomorTujuan(),
                                    AppCommonUtil.toRupiahFormat(dto.getTotalTransaksi()));
                    dto.setDeskripsi(deskripsi);
                    dtos.add(dto);
                } else if (t.getKategori().equals(EnumKategoriTransaksi.GOPAY.name()) ||
                        t.getKategori().equals(EnumKategoriTransaksi.DANA.name()) ||
                        t.getKategori().equals(EnumKategoriTransaksi.GRAB.name()) ||
                        t.getKategori().equals(EnumKategoriTransaksi.OVO.name()) ||
                        t.getKategori().equals(EnumKategoriTransaksi.SHOOPE_PAY.name())) {
                    DigitalDto digitalDto = digitalService.getTransaksiByIdTransaksi(t.getId());
                    HistoryDto<DigitalDto> dto = new HistoryDto<>();
                    dto.setDetailTransaksi(digitalDto);
                    setTransaksi(dto, t);

                    String deskripsi = String
                            .format("Pembelian paket %s ke no. %s berhasil, harga Rp. %s",
                                    digitalDto.getNamaPaket(), digitalDto.getNomorTujuan(),
                                    AppCommonUtil.toRupiahFormat(dto.getTotalTransaksi()));
                    dto.setDeskripsi(deskripsi);
                    dtos.add(dto);
                }
            }
        }
        return dtos;
    }

    private HistoryDto setTransaksi(HistoryDto dto, Transaksi t) {
        dto.setIdUser(t.getUserId());
        dto.setIdTransaksi(t.getId());
        dto.setTotalTransaksi(t.getTotalTransaksi().setScale(0, BigDecimal.ROUND_FLOOR).toString());
        dto.setKategori(t.getKategori());

        LocalDateTime dateTime = t.getTglTransaksi().toLocalDateTime();
        dto.setTglTransaksi(dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag("id-ID"))));
        dto.setJamTransaksi(dateTime.format(DateTimeFormatter.ofPattern("HH:mm")));
        return dto;
    }
}