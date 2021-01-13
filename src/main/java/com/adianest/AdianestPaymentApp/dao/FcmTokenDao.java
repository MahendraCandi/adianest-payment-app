package com.adianest.AdianestPaymentApp.dao;

import com.adianest.AdianestPaymentApp.model.FcmToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FcmTokenDao  extends JpaRepository<FcmToken, String> {
}
