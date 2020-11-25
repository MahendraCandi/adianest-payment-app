/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.dto.TopUpDto;
import com.adianest.AdianestPaymentApp.service.ITopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/topup")
public class TopUpController {

    @Autowired
    private ITopUpService topUpService;

    @PostMapping(value = "/confirm/insert")
    public TopUpDto insertConfirmTopUp(@RequestBody TopUpDto dto) {
        return topUpService.insertConfirmTopUp(dto);
    }

    @PostMapping(value = "/confirm/get")
    public TopUpDto getConfirmTopUp(@RequestBody TopUpDto dto) {
        return topUpService.getConfirmTopUp(dto);
    }

    @PostMapping("/insert")
    public boolean insertTopUp(@RequestBody TopUpDto dto) {
        return topUpService.insertTopUp(dto);
    }
}
