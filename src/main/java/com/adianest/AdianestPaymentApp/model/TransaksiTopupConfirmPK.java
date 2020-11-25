/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TransaksiTopupConfirmPK implements Serializable {
    private String userId;
    private String transaksiId;
    private String kodePembayaran;

    @Column(name = "user_id", nullable = false, length = 20)
    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "transaksi_id", nullable = false, length = 50)
    @Id
    public String getTransaksiId() {
        return transaksiId;
    }

    public void setTransaksiId(String transaksiId) {
        this.transaksiId = transaksiId;
    }

    @Column(name = "kode_pembayaran", nullable = false, length = 50)
    @Id
    public String getKodePembayaran() {
        return kodePembayaran;
    }

    public void setKodePembayaran(String kodePembayaran) {
        this.kodePembayaran = kodePembayaran;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransaksiTopupConfirmPK that = (TransaksiTopupConfirmPK) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(transaksiId, that.transaksiId) &&
                Objects.equals(kodePembayaran, that.kodePembayaran);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, transaksiId, kodePembayaran);
    }
}
