package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.dto.SmsDto;
import com.adianest.AdianestPaymentApp.service.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Autowired
    ISmsService smsService;

    @GetMapping("/all")
    public List<SmsDto> getAllPaket() {
        return smsService.getAllPacket();
    }

    @PostMapping("insert")
    public boolean insertTransaksi(@RequestBody SmsDto dto) {
        return smsService.insertPaket(dto);
    }

}
