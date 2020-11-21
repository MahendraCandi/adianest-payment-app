/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "kategori_top_up", schema = "adianest_payment", catalog = "")
public class KategoriTopUp {
    private String id;
    private String deskripsi;
    private String prefix;

    @Id
    @Column(name = "id", nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "deskripsi", nullable = true, length = 50)
    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KategoriTopUp that = (KategoriTopUp) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(deskripsi, that.deskripsi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deskripsi);
    }

    @Basic
    @Column(name = "prefix", nullable = false, length = 5)
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
