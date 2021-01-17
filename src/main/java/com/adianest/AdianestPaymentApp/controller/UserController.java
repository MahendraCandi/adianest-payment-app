/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.dto.SaldoDto;
import com.adianest.AdianestPaymentApp.dto.UserDto;
import com.adianest.AdianestPaymentApp.service.ISaldo;
import com.adianest.AdianestPaymentApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    ISaldo saldoService;

    @GetMapping("/get/all")
    public ResponseEntity<List<UserDto>> getAllUsers (){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") String userId) {
        return ResponseEntity.ok(userService.getOneUserById(userId));
    }

    @GetMapping("/get/phone/{phone}")
    public ResponseEntity<UserDto> getUserByPhoneNumber(@PathVariable("phone") String phoneNumber) {
        try{
//            Thread.sleep(3000L);
        }catch(Exception e){
            e.printStackTrace();
        }

        UserDto dto = userService.getOneUserByNoTelpon(phoneNumber);
        SaldoDto saldoDto = saldoService.getEndingBalanceByUserIdAsDto(dto.getIdUser());
        dto.setEndingBalance(saldoDto.getEndingBalance());
        dto.setPasswordUser(null);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/insert")
    public ResponseEntity insertUser (@RequestBody @Valid UserDto userDto) {
        System.out.println("1");
        return ResponseEntity.ok(userService.insertUser(userDto));
    }

    @PostMapping("/update")
    public ResponseEntity updateUser (@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @PostMapping("/delete")
    public ResponseEntity deleteUser (@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.deleteUser(userDto.getIdUser()));
    }
}
