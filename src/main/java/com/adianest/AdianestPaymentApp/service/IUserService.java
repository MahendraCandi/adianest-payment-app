/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.dto.UserDto;
import com.adianest.AdianestPaymentApp.model.User;

import java.util.List;

public interface IUserService {

    boolean insertUser(UserDto userDto);

    UserDto getOneUserById(String id);

    UserDto getOneUserByNoTelpon(String noTelpon);

    List<UserDto> getAllUsers();

    boolean deleteUser(String id);

    UserDto updateUser(UserDto userDto);

}
