package com.adianest.AdianestPaymentApp.service;

import org.springframework.web.multipart.MultipartFile;

public interface IMediaHandler {

    String uploadFile(MultipartFile file, String idUser);

    String getPathFileByIdUser(String idUser);

    byte[] getPhoto(String idPhoto);
}
