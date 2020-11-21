/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.model.KategoriTopUp;
import com.adianest.AdianestPaymentApp.service.IKategoriTopUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/kategori/topup", produces = MediaType.APPLICATION_JSON_VALUE)
public class KategoriTopUpController {

    @Autowired
    private IKategoriTopUp kategoriTopUpService;

    @GetMapping("/all")
    public List<KategoriTopUp> getAllKategoriTopUp() {
        return kategoriTopUpService.getAllCategory();
    }

    @GetMapping("/{id}")
    public KategoriTopUp findByKategoriId(@PathVariable("id") String id) {
        return kategoriTopUpService.findCategoryById(id);
    }
}
