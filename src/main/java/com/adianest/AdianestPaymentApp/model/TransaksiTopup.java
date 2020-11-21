/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "transaksi_topup", schema = "adianest_payment", catalog = "")
public class TransaksiTopup {
    private String transaksiId;
    private String kategori;
    private String kodePembayaran;
    private BigDecimal nominal;

    @Id
    @Column(name = "transaksi_id", nullable = false, length = 50)
    public String getTransaksiId() {
        return transaksiId;
    }

    public void setTransaksiId(String transaksiId) {
        this.transaksiId = transaksiId;
    }

    @Basic
    @Column(name = "kategori", nullable = false, length = 100)
    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    @Basic
    @Column(name = "kode_pembayaran", nullable = false, length = 100)
    public String getKodePembayaran() {
        return kodePembayaran;
    }

    public void setKodePembayaran(String kodePembayaran) {
        this.kodePembayaran = kodePembayaran;
    }

    @Basic
    @Column(name = "nominal", nullable = false, precision = 2)
    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransaksiTopup that = (TransaksiTopup) o;
        return Objects.equals(transaksiId, that.transaksiId) &&
                Objects.equals(kategori, that.kategori) &&
                Objects.equals(kodePembayaran, that.kodePembayaran) &&
                Objects.equals(nominal, that.nominal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transaksiId, kategori, kodePembayaran, nominal);
    }
}
