/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "transaksi_topup_confirm", schema = "adianest_payment", catalog = "")
@IdClass(TransaksiTopupConfirmPK.class)
public class TransaksiTopupConfirm {
    private String userId;
    private String noTelpon;
    private String kategoriTopUp;
    private String transaksiId;
    private BigDecimal totalAmount;
    private String kodePembayaran;
    private Boolean konfirmasiTransaksi;
    private BigDecimal nominalTopup;
    private String kategoriTransaksi;

    @Id
    @Column(name = "user_id", nullable = false, length = 20)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "no_telpon", nullable = false, length = 20)
    public String getNoTelpon() {
        return noTelpon;
    }

    public void setNoTelpon(String noTelpon) {
        this.noTelpon = noTelpon;
    }

    @Basic
    @Column(name = "kategori_top_up", nullable = false, length = 20)
    public String getKategoriTopUp() {
        return kategoriTopUp;
    }

    public void setKategoriTopUp(String kategoriTopUp) {
        this.kategoriTopUp = kategoriTopUp;
    }

    @Id
    @Column(name = "transaksi_id", nullable = false, length = 50)
    public String getTransaksiId() {
        return transaksiId;
    }

    public void setTransaksiId(String transaksiId) {
        this.transaksiId = transaksiId;
    }

    @Basic
    @Column(name = "total_amount", nullable = false, precision = 2)
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Id
    @Column(name = "kode_pembayaran", nullable = false, length = 50)
    public String getKodePembayaran() {
        return kodePembayaran;
    }

    public void setKodePembayaran(String kodePembayaran) {
        this.kodePembayaran = kodePembayaran;
    }

    @Basic
    @Column(name = "konfirmasi_transaksi", nullable = false, columnDefinition = "TINYINT(1)")
    public Boolean getKonfirmasiTransaksi() {
        return konfirmasiTransaksi;
    }

    public void setKonfirmasiTransaksi(Boolean konfirmasiTransaksi) {
        this.konfirmasiTransaksi = konfirmasiTransaksi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransaksiTopupConfirm that = (TransaksiTopupConfirm) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(noTelpon, that.noTelpon) &&
                Objects.equals(kategoriTopUp, that.kategoriTopUp) &&
                Objects.equals(transaksiId, that.transaksiId) &&
                Objects.equals(totalAmount, that.totalAmount) &&
                Objects.equals(kodePembayaran, that.kodePembayaran) &&
                Objects.equals(konfirmasiTransaksi, that.konfirmasiTransaksi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, noTelpon, kategoriTopUp, transaksiId, totalAmount, kodePembayaran, konfirmasiTransaksi);
    }

    @Basic
    @Column(name = "nominal_topup", nullable = false, precision = 2)
    public BigDecimal getNominalTopup() {
        return nominalTopup;
    }

    public void setNominalTopup(BigDecimal nominalTopup) {
        this.nominalTopup = nominalTopup;
    }

    @Basic
    @Column(name = "kategori_transaksi", nullable = false, length = 50)
    public String getKategoriTransaksi() {
        return kategoriTransaksi;
    }

    public void setKategoriTransaksi(String kategoriTransaksi) {
        this.kategoriTransaksi = kategoriTransaksi;
    }
}
