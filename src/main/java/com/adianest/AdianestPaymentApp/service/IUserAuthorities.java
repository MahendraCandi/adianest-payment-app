/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.model.UserAuthorities;

import java.util.Set;

public interface IUserAuthorities {

    boolean insertUserAuthorities(UserAuthorities userAuthorities);

    Set<UserAuthorities> getUserAuthoritiesByUserId(String id);
}
