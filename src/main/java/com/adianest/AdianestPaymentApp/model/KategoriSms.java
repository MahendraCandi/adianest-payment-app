/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "kategori_sms", schema = "adianest_payment", catalog = "")
public class KategoriSms {
    private String id;
    private String samaOperator;
    private String bedaOperator;
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
    @Column(name = "sama_operator", nullable = false, length = 10)
    public String getSamaOperator() {
        return samaOperator;
    }

    public void setSamaOperator(String samaOperator) {
        this.samaOperator = samaOperator;
    }

    @Basic
    @Column(name = "beda_operator", nullable = false, length = 10)
    public String getBedaOperator() {
        return bedaOperator;
    }

    public void setBedaOperator(String bedaOperator) {
        this.bedaOperator = bedaOperator;
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
        KategoriSms that = (KategoriSms) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(samaOperator, that.samaOperator) &&
                Objects.equals(bedaOperator, that.bedaOperator) &&
                Objects.equals(harga, that.harga);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, samaOperator, bedaOperator, harga);
    }
}
