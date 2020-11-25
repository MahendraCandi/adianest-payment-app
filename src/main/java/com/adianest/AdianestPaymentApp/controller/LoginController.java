/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.jwt.JwtResponseBody;
import com.adianest.AdianestPaymentApp.jwt.UsernameAndPasswordAuthenticationRequest;
import com.adianest.AdianestPaymentApp.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    ILoginService loginService;

    @PostMapping("/user")
    public JwtResponseBody loginUser(@RequestBody UsernameAndPasswordAuthenticationRequest authenticationRequest) {
        return loginService.doLoginUser(authenticationRequest);
    }
}
