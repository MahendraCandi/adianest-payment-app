/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.dto.UserDto;
import com.adianest.AdianestPaymentApp.model.UserPrincipal;
import com.adianest.AdianestPaymentApp.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private static final Logger log = LogManager.getLogger(AppUserDetailsService.class);

    @Autowired
    IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) {
        UserDto userDto = userService.getOneUserByNoTelpon(s);
        if (userDto == null) {
            log.error("User %s not found", s);
//            throw  new UsernameNotFoundException(String.format("User %s not found", s));
        }

        return new UserPrincipal(userDto);
    }
}
