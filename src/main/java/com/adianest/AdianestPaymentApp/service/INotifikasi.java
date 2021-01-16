package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.model.Notifikasi;

import java.sql.Timestamp;
import java.util.List;

public interface INotifikasi {

    boolean insertNotifikasi(Notifikasi notifikasi);

    boolean insertNotifikasi(String userId, String transaksiId, String transaksiKategori, String message, Timestamp tglTransaksi);

    List<Notifikasi> getAllByUserId(String userId);

    boolean updateAllNotifikasiByUserId(String userId);

    boolean updateNotifikasiByIdTransaksi(String idTransaksi);
}
