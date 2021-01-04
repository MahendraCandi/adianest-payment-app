package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "kategori_digitial", schema = "adianest_payment", catalog = "")
public class KategoriDigitial {
    private String id;
    private String jumlah;
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
    @Column(name = "jumlah", nullable = false, length = 10)
    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    @Basic
    @Column(name = "harga", nullable = false, precision = 2)
    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KategoriDigitial that = (KategoriDigitial) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(jumlah, that.jumlah) &&
                Objects.equals(harga, that.harga);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jumlah, harga);
    }
}
