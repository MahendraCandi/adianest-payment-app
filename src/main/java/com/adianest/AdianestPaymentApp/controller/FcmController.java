package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.service.IFcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fcm")
public class FcmController {

    @Autowired
    private IFcmService fcmService;

    @PostMapping("/insert")
    public boolean insertFcmToken (
            @RequestParam("id") String id,
            @RequestParam("token") String token) {
        return fcmService.insertToken(id, token);
    }
}
