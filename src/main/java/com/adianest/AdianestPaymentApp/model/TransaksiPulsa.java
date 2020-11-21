/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "transaksi_pulsa", schema = "adianest_payment", catalog = "")
public class TransaksiPulsa {
    private String transaksiId;
    private String nomorDari;
    private String nomorTujuan;
    private BigDecimal jumlahPulsa;

    @Id
    @Column(name = "transaksi_id", nullable = false, length = 50)
    public String getTransaksiId() {
        return transaksiId;
    }

    public void setTransaksiId(String transaksiId) {
        this.transaksiId = transaksiId;
    }

    @Basic
    @Column(name = "nomor_dari", nullable = false, length = 20)
    public String getNomorDari() {
        return nomorDari;
    }

    public void setNomorDari(String nomorDari) {
        this.nomorDari = nomorDari;
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
    @Column(name = "jumlah_pulsa", nullable = false, precision = 2)
    public BigDecimal getJumlahPulsa() {
        return jumlahPulsa;
    }

    public void setJumlahPulsa(BigDecimal jumlahPulsa) {
        this.jumlahPulsa = jumlahPulsa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransaksiPulsa that = (TransaksiPulsa) o;
        return Objects.equals(transaksiId, that.transaksiId) &&
                Objects.equals(nomorDari, that.nomorDari) &&
                Objects.equals(nomorTujuan, that.nomorTujuan) &&
                Objects.equals(jumlahPulsa, that.jumlahPulsa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transaksiId, nomorDari, nomorTujuan, jumlahPulsa);
    }
}
