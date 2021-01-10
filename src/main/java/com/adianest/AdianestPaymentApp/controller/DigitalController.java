package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.dto.DigitalDto;
import com.adianest.AdianestPaymentApp.service.IDigitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/digital")
public class DigitalController {

    @Autowired
    private IDigitalService digitalService;

    @GetMapping("/all")
    List<DigitalDto> getAllKategori() {
        return digitalService.getAllKategori();
    }

    @PostMapping("/insert")
    boolean insertKategori(@RequestBody DigitalDto dto) {
        return digitalService.insertTransaksi(dto);
    }
}
