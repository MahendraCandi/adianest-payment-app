/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.dto.InternetDto;
import com.adianest.AdianestPaymentApp.service.IInternetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/internet", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaketInternetController {

    @Autowired
    IInternetService internetService;

    @GetMapping("/paket/all")
    public List<InternetDto> getAllPaket() {
        return internetService.getAllPaketInternet();
    }

    @PostMapping("/paket/insert")
    public boolean insertTransaksi(@RequestBody InternetDto dto) {
        return internetService.insertTransaksi(dto);
    }
}
