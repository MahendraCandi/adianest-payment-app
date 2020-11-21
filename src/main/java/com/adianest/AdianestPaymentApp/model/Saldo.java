/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Saldo {
    private Integer id;
    private String userId;
    private BigDecimal saldoAwal;
    private BigDecimal nilaiMutasi;
    private BigDecimal saldoAkhir;
    private Timestamp tglMutasi;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "saldo_awal", nullable = false, precision = 0)
    public BigDecimal getSaldoAwal() {
        return saldoAwal;
    }

    public void setSaldoAwal(BigDecimal saldoAwal) {
        this.saldoAwal = saldoAwal;
    }

    @Basic
    @Column(name = "nilai_mutasi", nullable = false, precision = 0)
    public BigDecimal getNilaiMutasi() {
        return nilaiMutasi;
    }

    public void setNilaiMutasi(BigDecimal nilaiMutasi) {
        this.nilaiMutasi = nilaiMutasi;
    }

    @Basic
    @Column(name = "saldo_akhir", nullable = false, precision = 0)
    public BigDecimal getSaldoAkhir() {
        return saldoAkhir;
    }

    public void setSaldoAkhir(BigDecimal saldoAkhir) {
        this.saldoAkhir = saldoAkhir;
    }

    @Basic
    @Column(name = "tgl_mutasi", nullable = true)
    public Timestamp getTglMutasi() {
        return tglMutasi;
    }

    public void setTglMutasi(Timestamp tglMutasi) {
        this.tglMutasi = tglMutasi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Saldo saldo = (Saldo) o;
        return Objects.equals(id, saldo.id) &&
                Objects.equals(userId, saldo.userId) &&
                Objects.equals(saldoAwal, saldo.saldoAwal) &&
                Objects.equals(nilaiMutasi, saldo.nilaiMutasi) &&
                Objects.equals(saldoAkhir, saldo.saldoAkhir) &&
                Objects.equals(tglMutasi, saldo.tglMutasi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, saldoAwal, nilaiMutasi, saldoAkhir, tglMutasi);
    }
}
