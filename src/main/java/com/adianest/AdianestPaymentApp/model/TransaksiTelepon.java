/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "transaksi_telepon", schema = "adianest_payment", catalog = "")
public class TransaksiTelepon {
    private String transaksiId;
    private String nomorTujuan;
    private String paketTelepon;
    private BigDecimal hargaPaket;

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
    @Column(name = "paket_telepon", nullable = false, length = 10)
    public String getPaketTelepon() {
        return paketTelepon;
    }

    public void setPaketTelepon(String paketTelepon) {
        this.paketTelepon = paketTelepon;
    }

    @Basic
    @Column(name = "harga_paket", nullable = false, precision = 2)
    public BigDecimal getHargaPaket() {
        return hargaPaket;
    }

    public void setHargaPaket(BigDecimal hargaPaket) {
        this.hargaPaket = hargaPaket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransaksiTelepon that = (TransaksiTelepon) o;
        return Objects.equals(transaksiId, that.transaksiId) &&
                Objects.equals(nomorTujuan, that.nomorTujuan) &&
                Objects.equals(paketTelepon, that.paketTelepon) &&
                Objects.equals(hargaPaket, that.hargaPaket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transaksiId, nomorTujuan, paketTelepon, hargaPaket);
    }
}
