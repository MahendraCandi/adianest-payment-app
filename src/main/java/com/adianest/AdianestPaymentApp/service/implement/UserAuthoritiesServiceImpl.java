/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.dao.UserAuthoritiesDao;
import com.adianest.AdianestPaymentApp.model.UserAuthorities;
import com.adianest.AdianestPaymentApp.service.IUserAuthorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserAuthoritiesServiceImpl implements IUserAuthorities {

    @Autowired
    UserAuthoritiesDao dao;

    @Override
    public boolean insertUserAuthorities(UserAuthorities userAuthorities) {
        UserAuthorities user = dao.save(userAuthorities);
        return user.getId() != null;
    }

    @Override
    public Set<UserAuthorities> getUserAuthoritiesByUserId(String id) {
        return dao.findByUserId(id);
    }
}
