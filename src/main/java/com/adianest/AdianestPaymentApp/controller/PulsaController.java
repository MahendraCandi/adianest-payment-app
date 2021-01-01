package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.model.PulsaDto;
import com.adianest.AdianestPaymentApp.service.IPulsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pulsa")
public class PulsaController {

    @Autowired
    IPulsaService pulsaService;

    // get all
    @GetMapping("/all")
    List<PulsaDto> getAllKategori() {
        return pulsaService.getAllKategori();
    }

    @PostMapping("/insert")
    boolean insertTransaksi(@RequestBody PulsaDto dto) {
        return pulsaService.insertTransaksi(dto);
    }

}
