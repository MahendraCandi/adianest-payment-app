/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.dao;

import com.adianest.AdianestPaymentApp.model.KategoriSms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriSmsDao extends JpaRepository<KategoriSms, String> {
}
