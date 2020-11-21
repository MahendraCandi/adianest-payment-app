/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.dao;

import com.adianest.AdianestPaymentApp.model.UserAuthorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface UserAuthoritiesDao extends JpaRepository<UserAuthorities, Integer> {

    @Query(value = "SELECT A FROM UserAuthorities A WHERE A.userId = :userId")
    Set<UserAuthorities> findByUserId(@Param("userId") String id);
}
