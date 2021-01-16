package com.adianest.AdianestPaymentApp.dao;

import com.adianest.AdianestPaymentApp.model.Notifikasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotifikasiDao extends JpaRepository<Notifikasi, Integer> {

    Optional<List<Notifikasi>> findAllByUserIdAndStatusIs(String userId, Integer status);

    Optional<List<Notifikasi>> findAllByUserId(String userId);

    Optional<Notifikasi> findByTransaksiId(String idTransaksi);
}
