/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.dto.UserDto;
import com.adianest.AdianestPaymentApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    IUserService userService;


    @PostMapping("/signup")
    public ResponseEntity insertUser (@Validated @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.insertUser(userDto));
    }
}
