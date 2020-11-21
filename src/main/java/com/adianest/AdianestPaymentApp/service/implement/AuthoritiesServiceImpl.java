/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.dao.AuthoritiesDao;
import com.adianest.AdianestPaymentApp.model.Authorities;
import com.adianest.AdianestPaymentApp.service.IAuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthoritiesServiceImpl implements IAuthoritiesService {

    @Autowired
    AuthoritiesDao authoritiesDao;

    @Override
    public Authorities getAuthoritiesById(Integer id) {
        return authoritiesDao.findById(id).orElse(null);
    }

    @Override
    public List<Authorities> getAllAuthorities() {
        return authoritiesDao.findAll();
    }

    @Override
    public boolean insertAuthorities(Authorities authorities) {
        return false;
    }

    @Override
    public Authorities updateAuthorities(Authorities authorities) {
        return null;
    }

    @Override
    public boolean deleteAuthorities(Integer Id) {
        return false;
    }
}
