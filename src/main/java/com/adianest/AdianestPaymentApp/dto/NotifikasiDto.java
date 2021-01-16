package com.adianest.AdianestPaymentApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotifikasiDto {
    private Integer id;
    private String userId;
    private String status;
    private String idTransaksi;
    private String kategori;
    private String pesan;
    private String tglTransaksi;

    public NotifikasiDto() {
    }

    public NotifikasiDto(Integer id, String userId, String status, String idTransaksi, String kategori, String pesan, String tglTransaksi) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.idTransaksi = idTransaksi;
        this.kategori = kategori;
        this.pesan = pesan;
        this.tglTransaksi = tglTransaksi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(String tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }
}
