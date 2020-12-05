/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "kategori_internet", schema = "adianest_payment", catalog = "")
public class KategoriInternet {
    private String id;
    private Integer jumlah;
    private String satuan;
    private String nama;
    private BigDecimal harga;

    @Id
    @Column(name = "id", nullable = false, length = 10)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "jumlah", nullable = false)
    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    @Basic
    @Column(name = "satuan", nullable = false, length = 10)
    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    @Basic
    @Column(name = "nama", nullable = false, length = 20)
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KategoriInternet that = (KategoriInternet) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(jumlah, that.jumlah) &&
                Objects.equals(satuan, that.satuan) &&
                Objects.equals(nama, that.nama);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jumlah, satuan, nama);
    }

    @Basic
    @Column(name = "harga", nullable = false, precision = 2)
    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }
}
