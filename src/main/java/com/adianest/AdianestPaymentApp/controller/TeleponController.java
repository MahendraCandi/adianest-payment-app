package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.dto.TeleponDto;
import com.adianest.AdianestPaymentApp.service.ITeleponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telepon")
public class TeleponController {

    @Autowired
    ITeleponService teleponService;

    @GetMapping("/all")
    public List<TeleponDto> getAllPaket() {
        return teleponService.getAllPaket();
    }


    @PostMapping("insert")
    public boolean insertTransaksi(@RequestBody TeleponDto dto) {
        return teleponService.insertPaket(dto);
    }
}
