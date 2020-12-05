package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.dto.HistoryDto;
import com.adianest.AdianestPaymentApp.service.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    IHistoryService historyService;

    // get all history
    @PostMapping("/all")
    public List<HistoryDto> getAllHistory(@RequestBody HistoryDto dto) {
        return historyService.getAllHistoryByIdUser(dto.getIdUser());
    }

    // get details transaction
}
