/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopUpDto {

    private String userId;
    private String noTelpon;
    private String kategoriTransaksi;
    private String kategoriTopUp;
    private String nominalTopUp;
    private String transaksiId;
    private String adminFee;
    private String totalAmount;
    private String kodePembayaran;
    boolean konfirmasiTransaksi;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKategoriTransaksi() {
        return kategoriTransaksi;
    }

    public void setKategoriTransaksi(String kategoriTransaksi) {
        this.kategoriTransaksi = kategoriTransaksi;
    }

    public String getKategoriTopUp() {
        return kategoriTopUp;
    }

    public void setKategoriTopUp(String kategoriTopUp) {
        this.kategoriTopUp = kategoriTopUp;
    }

    public String getNominalTopUp() {
        return nominalTopUp;
    }

    public void setNominalTopUp(String nominalTopUp) {
        this.nominalTopUp = nominalTopUp;
    }

    public String getTransaksiId() {
        return transaksiId;
    }

    public void setTransaksiId(String transaksiId) {
        this.transaksiId = transaksiId;
    }

    public String getAdminFee() {
        return adminFee;
    }

    public void setAdminFee(String adminFee) {
        this.adminFee = adminFee;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getKodePembayaran() {
        return kodePembayaran;
    }

    public void setKodePembayaran(String kodePembayaran) {
        this.kodePembayaran = kodePembayaran;
    }

    public boolean isKonfirmasiTransaksi() {
        return konfirmasiTransaksi;
    }

    public void setKonfirmasiTransaksi(boolean konfirmasiTransaksi) {
        this.konfirmasiTransaksi = konfirmasiTransaksi;
    }

    public String getNoTelpon() {
        return noTelpon;
    }

    public void setNoTelpon(String noTelpon) {
        this.noTelpon = noTelpon;
    }
}
