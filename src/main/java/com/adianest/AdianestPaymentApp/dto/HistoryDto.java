package com.adianest.AdianestPaymentApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoryDto<T> {
    private String idUser;
    private String idTransaksi;
    private String totalTransaksi;
    private String tglTransaksi;
    private String jamTransaksi;
    private String kategori;
    private T detailTransaksi;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getTotalTransaksi() {
        return totalTransaksi;
    }

    public void setTotalTransaksi(String totalTransaksi) {
        this.totalTransaksi = totalTransaksi;
    }

    public String getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(String tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public String getJamTransaksi() {
        return jamTransaksi;
    }

    public void setJamTransaksi(String jamTransaksi) {
        this.jamTransaksi = jamTransaksi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public T getDetailTransaksi() {
        return detailTransaksi;
    }

    public void setDetailTransaksi(T detailTransaksi) {
        this.detailTransaksi = detailTransaksi;
    }
}
