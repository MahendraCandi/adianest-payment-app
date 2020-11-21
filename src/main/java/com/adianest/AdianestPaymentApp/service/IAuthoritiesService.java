/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.model.Authorities;

import java.util.List;

public interface IAuthoritiesService {

    Authorities getAuthoritiesById(Integer id);

    List<Authorities> getAllAuthorities();

    boolean insertAuthorities(Authorities authorities);

    Authorities updateAuthorities(Authorities authorities);

    boolean deleteAuthorities(Integer Id);
}
