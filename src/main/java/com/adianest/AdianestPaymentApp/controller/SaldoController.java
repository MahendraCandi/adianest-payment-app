/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.dto.SaldoDto;
import com.adianest.AdianestPaymentApp.service.ISaldo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/saldo")
public class SaldoController {

    @Autowired
    ISaldo saldoService;

    @PostMapping("/balance")
    public SaldoDto getEndingBalance(@RequestBody  SaldoDto saldoDto) {
        return saldoService.getEndingBalanceByUserIdAsDto(saldoDto.getUserId());
    }
}
