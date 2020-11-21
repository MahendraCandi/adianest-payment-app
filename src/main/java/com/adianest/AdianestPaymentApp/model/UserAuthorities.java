/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_authorities", schema = "adianest_payment", catalog = "")
public class UserAuthorities {
    private Integer id;
    private String userId;
    private Integer authoritiesId;

    public UserAuthorities() {
    }

    public UserAuthorities(String userId, Integer authoritiesId) {
        this.userId = userId;
        this.authoritiesId = authoritiesId;
    }

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
    @Column(name = "authorities_id", nullable = false)
    public Integer getAuthoritiesId() {
        return authoritiesId;
    }

    public void setAuthoritiesId(Integer authoritiesId) {
        this.authoritiesId = authoritiesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthorities that = (UserAuthorities) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(authoritiesId, that.authoritiesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, authoritiesId);
    }
}
