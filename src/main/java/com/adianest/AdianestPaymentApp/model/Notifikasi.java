package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Notifikasi {
    private Integer id;
    private String userId;
    private Integer status;
    private String transaksiId;
    private String transaksiKategori;
    private String message;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "transaksi_id", nullable = false, length = 50)
    public String getTransaksiId() {
        return transaksiId;
    }

    public void setTransaksiId(String transaksiId) {
        this.transaksiId = transaksiId;
    }

    @Basic
    @Column(name = "transaksi_kategori", nullable = false, length = 50)
    public String getTransaksiKategori() {
        return transaksiKategori;
    }

    public void setTransaksiKategori(String transaksiKategori) {
        this.transaksiKategori = transaksiKategori;
    }

    @Basic
    @Column(name = "message", nullable = false, length = 255)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notifikasi that = (Notifikasi) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(status, that.status) &&
                Objects.equals(transaksiId, that.transaksiId) &&
                Objects.equals(transaksiKategori, that.transaksiKategori) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, status, transaksiId, transaksiKategori, message);
    }
}
