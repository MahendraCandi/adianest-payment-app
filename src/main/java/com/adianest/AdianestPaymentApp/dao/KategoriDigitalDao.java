/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.dao;

import com.adianest.AdianestPaymentApp.model.KategoriDigitial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriDigitalDao extends JpaRepository<KategoriDigitial, String> {
}
