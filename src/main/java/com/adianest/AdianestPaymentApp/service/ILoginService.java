/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.jwt.JwtResponseBody;
import com.adianest.AdianestPaymentApp.jwt.UsernameAndPasswordAuthenticationRequest;

public interface ILoginService {

    JwtResponseBody doLoginUser(UsernameAndPasswordAuthenticationRequest authenticationRequest);

}
