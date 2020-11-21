/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Transaksi {
    private String id;
    private String userId;
    private BigDecimal totalTransaksi;
    private Timestamp tglTransaksi;
    private String kategori;

    @Id
    @Column(name = "id", nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false, length = 20)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "total_transaksi", nullable = false, precision = 2)
    public BigDecimal getTotalTransaksi() {
        return totalTransaksi;
    }

    public void setTotalTransaksi(BigDecimal totalTransaksi) {
        this.totalTransaksi = totalTransaksi;
    }

    @Basic
    @Column(name = "tgl_transaksi", nullable = false)
    public Timestamp getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(Timestamp tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    @Basic
    @Column(name = "kategori", nullable = false, length = 50)
    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaksi transaksi = (Transaksi) o;
        return Objects.equals(id, transaksi.id) &&
                Objects.equals(userId, transaksi.userId) &&
                Objects.equals(totalTransaksi, transaksi.totalTransaksi) &&
                Objects.equals(tglTransaksi, transaksi.tglTransaksi) &&
                Objects.equals(kategori, transaksi.kategori);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, totalTransaksi, tglTransaksi, kategori);
    }
}
