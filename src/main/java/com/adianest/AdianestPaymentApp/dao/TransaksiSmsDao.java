/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.dao;

import com.adianest.AdianestPaymentApp.model.TransaksiSms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaksiSmsDao  extends JpaRepository<TransaksiSms, String> {
}
