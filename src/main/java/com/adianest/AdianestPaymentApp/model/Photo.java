/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Photo {
    private Integer id;
    private String namaFile;
    private String pathFile;

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
    @Column(name = "nama_file", nullable = false, length = 100)
    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    @Basic
    @Column(name = "path_file", nullable = false, length = 100)
    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id) &&
                Objects.equals(namaFile, photo.namaFile) &&
                Objects.equals(pathFile, photo.pathFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, namaFile, pathFile);
    }
}
