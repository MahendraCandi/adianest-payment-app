package com.adianest.AdianestPaymentApp.dao;

import com.adianest.AdianestPaymentApp.model.KategoriPulsa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KategoriPulsaDao extends JpaRepository<KategoriPulsa, String> {
}
