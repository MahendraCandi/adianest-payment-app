/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.dao;

import com.adianest.AdianestPaymentApp.model.KategoriTelepon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriTeleponDao extends JpaRepository<KategoriTelepon, String> {
}
