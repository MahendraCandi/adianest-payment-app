package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.dto.UserDto;
import com.adianest.AdianestPaymentApp.service.IMediaHandler;
import com.adianest.AdianestPaymentApp.service.IUserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    IUserService userService;

    @Autowired
    IMediaHandler mediaHandler;

    @GetMapping(value = "/picture/{id}",
            produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public @ResponseBody byte[] getProfilePicture(@PathVariable("id") String idPhoto) {

        return mediaHandler.getPhoto(idPhoto);
    }

    @PostMapping("/update")
    public UserDto updateProfile(
            @RequestParam(value = "photo", required = false) MultipartFile file,
            @RequestParam("namaLengkap") String namaLengkap,
            @RequestParam("nomorPonsel") String nomorPonsel,
            @RequestParam("alamatEmail") String email,
            @RequestParam("idUser") String idUser,
            @RequestParam("authorities") String authorities
    ) {
        UserDto dto = new UserDto();
        dto.setIdUser(idUser);
        dto.setNameUser(namaLengkap);
        dto.setNoTelpon(nomorPonsel);
        dto.setEmailUser(email);
        dto.setIdAuthorities(Integer.parseInt(authorities));

        if (file != null && !file.isEmpty()) {
            String pathFile = mediaHandler.uploadFile(file, idUser);
            if (pathFile == null) throw new IllegalStateException("Failed update photo!");

            dto.setPathPhoto(pathFile);
            dto.setNamaPhoto(FilenameUtils.getName(pathFile));
        }

        return userService.updateUser(dto);
    }
}
