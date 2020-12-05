/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "transaksi_digital", schema = "adianest_payment", catalog = "")
public class TransaksiDigital {
    private String transaksiId;
    private String nomorTujuan;
    private BigDecimal nominalTransfer;
    private String kategoriId;

    @Id
    @Column(name = "transaksi_id", nullable = false, length = 50)
    public String getTransaksiId() {
        return transaksiId;
    }

    public void setTransaksiId(String transaksiId) {
        this.transaksiId = transaksiId;
    }

    @Basic
    @Column(name = "nomor_tujuan", nullable = false, length = 20)
    public String getNomorTujuan() {
        return nomorTujuan;
    }

    public void setNomorTujuan(String nomorTujuan) {
        this.nomorTujuan = nomorTujuan;
    }

    @Basic
    @Column(name = "nominal_transfer", nullable = false, precision = 2)
    public BigDecimal getNominalTransfer() {
        return nominalTransfer;
    }

    public void setNominalTransfer(BigDecimal nominalTransfer) {
        this.nominalTransfer = nominalTransfer;
    }

    @Basic
    @Column(name = "kategori_id", nullable = false, length = 10)
    public String getKategoriId() {
        return kategoriId;
    }

    public void setKategoriId(String kategoriId) {
        this.kategoriId = kategoriId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransaksiDigital that = (TransaksiDigital) o;
        return Objects.equals(transaksiId, that.transaksiId) &&
                Objects.equals(nomorTujuan, that.nomorTujuan) &&
                Objects.equals(nominalTransfer, that.nominalTransfer) &&
                Objects.equals(kategoriId, that.kategoriId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transaksiId, nomorTujuan, nominalTransfer, kategoriId);
    }
}
