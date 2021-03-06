/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private String idUser;

    @NotNull
    @NotEmpty
    private String noTelpon;

    @NotNull
    @NotEmpty
    private String passwordUser;

    @NotNull
    @NotEmpty
    private String emailUser;

    @NotNull
    @NotEmpty
    private String nameUser;
    private Integer idAuthorities;
    private String nameAuthorities;
    private String endingBalance;
    private String idPhoto;
    private String pathPhoto;
    private String namaPhoto;
    private String totalUnreadNotif;

    public String getTotalUnreadNotif() {
        return totalUnreadNotif;
    }

    public void setTotalUnreadNotif(String totalUnreadNotif) {
        this.totalUnreadNotif = totalUnreadNotif;
    }

    public String getNameAuthorities() {
        return nameAuthorities;
    }

    public void setNameAuthorities(String nameAuthorities) {
        this.nameAuthorities = nameAuthorities;
    }

    public Integer getIdAuthorities() {
        return idAuthorities;
    }

    public void setIdAuthorities(Integer idAuthorities) {
        this.idAuthorities = idAuthorities;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNoTelpon() {
        return noTelpon;
    }

    public void setNoTelpon(String noTelpon) {
        this.noTelpon = noTelpon;
    }

    public String getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(String endingBalance) {
        this.endingBalance = endingBalance;
    }

    public String getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(@Nullable String idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getPathPhoto() {
        return pathPhoto;
    }

    public void setPathPhoto(String pathPhoto) {
        this.pathPhoto = pathPhoto;
    }

    public String getNamaPhoto() {
        return namaPhoto;
    }

    public void setNamaPhoto(String namaPhoto) {
        this.namaPhoto = namaPhoto;
    }
}
