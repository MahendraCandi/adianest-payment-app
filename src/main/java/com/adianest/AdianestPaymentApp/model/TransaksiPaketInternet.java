/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "transaksi_paket_internet", schema = "adianest_payment", catalog = "")
public class TransaksiPaketInternet {
    private String transaksiId;
    private String nomorTujuan;
    private String paketKuota;
    private BigDecimal hargaKuota;

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
    @Column(name = "paket_kuota", nullable = false, length = 20)
    public String getPaketKuota() {
        return paketKuota;
    }

    public void setPaketKuota(String paketKuota) {
        this.paketKuota = paketKuota;
    }

    @Basic
    @Column(name = "harga_kuota", nullable = false, precision = 2)
    public BigDecimal getHargaKuota() {
        return hargaKuota;
    }

    public void setHargaKuota(BigDecimal hargaKuota) {
        this.hargaKuota = hargaKuota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransaksiPaketInternet that = (TransaksiPaketInternet) o;
        return Objects.equals(transaksiId, that.transaksiId) &&
                Objects.equals(nomorTujuan, that.nomorTujuan) &&
                Objects.equals(paketKuota, that.paketKuota) &&
                Objects.equals(hargaKuota, that.hargaKuota);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transaksiId, nomorTujuan, paketKuota, hargaKuota);
    }
}
