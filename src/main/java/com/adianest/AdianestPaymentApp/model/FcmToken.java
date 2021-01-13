package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "fcm_token", schema = "adianest_payment", catalog = "")
public class FcmToken {
    private String id;
    private String token;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    @Id
    @Basic
    @Column(name = "id", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "token", nullable = false, length = 255)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "created_date", nullable = true)
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "modified_date", nullable = true)
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FcmToken fcmToken = (FcmToken) o;
        return Objects.equals(id, fcmToken.id) &&
                Objects.equals(token, fcmToken.token) &&
                Objects.equals(createdDate, fcmToken.createdDate) &&
                Objects.equals(modifiedDate, fcmToken.modifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, createdDate, modifiedDate);
    }
}
