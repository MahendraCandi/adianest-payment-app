/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.controller;

import com.adianest.AdianestPaymentApp.dto.UserDto;
import com.adianest.AdianestPaymentApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/get/all")
    public ResponseEntity<List<UserDto>> getAllUsers (){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") String userId) {
        return ResponseEntity.ok(userService.getOneUserById(userId));
    }

    @PostMapping("/insert")
    public ResponseEntity insertUser (@RequestBody UserDto userDto) {
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
