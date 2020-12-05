/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "kategori_telepon", schema = "adianest_payment", catalog = "")
public class KategoriTelepon {
    private String id;
    private String sesamaOperator;
    private String bedaOperator;
    private BigDecimal hargaPaket;

    @Id
    @Column(name = "id", nullable = false, length = 10)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sesama_operator", nullable = false, length = 10)
    public String getSesamaOperator() {
        return sesamaOperator;
    }

    public void setSesamaOperator(String sesamaOperator) {
        this.sesamaOperator = sesamaOperator;
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
        KategoriTelepon that = (KategoriTelepon) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(sesamaOperator, that.sesamaOperator) &&
                Objects.equals(bedaOperator, that.bedaOperator) &&
                Objects.equals(hargaPaket, that.hargaPaket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sesamaOperator, bedaOperator, hargaPaket);
    }
}
