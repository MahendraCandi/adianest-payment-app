/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.dao;

import com.adianest.AdianestPaymentApp.model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransaksiDao extends JpaRepository<Transaksi, String> {

    Optional<Transaksi> findTopByIdStartsWithOrderByIdDesc(String prefix);

    List<Transaksi> findAllByUserIdOrderByTglTransaksiDesc(String idUser);
}
