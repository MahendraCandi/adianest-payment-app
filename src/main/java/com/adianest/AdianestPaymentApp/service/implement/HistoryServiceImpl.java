package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.dto.HistoryDto;
import com.adianest.AdianestPaymentApp.dto.InternetDto;
import com.adianest.AdianestPaymentApp.dto.TopUpDto;
import com.adianest.AdianestPaymentApp.model.EnumKategoriTransaksi;
import com.adianest.AdianestPaymentApp.model.Transaksi;
import com.adianest.AdianestPaymentApp.service.IHistoryService;
import com.adianest.AdianestPaymentApp.service.IInternetService;
import com.adianest.AdianestPaymentApp.service.ITopUpService;
import com.adianest.AdianestPaymentApp.service.ITransaksiService;
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

                    dtos.add(dto);
                } else if (t.getKategori().equals(EnumKategoriTransaksi.PAKET_INTERNET.name())) {
                    InternetDto internetDto = internetService.getTransaksiByIdTransaksi(t.getId());
                    HistoryDto<InternetDto> dto = new HistoryDto<>();
                    dto.setDetailTransaksi(internetDto);
                    setTransaksi(dto, t);

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