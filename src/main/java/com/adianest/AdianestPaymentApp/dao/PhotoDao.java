package com.adianest.AdianestPaymentApp.dao;

import com.adianest.AdianestPaymentApp.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoDao extends JpaRepository<Photo, Integer> {

    Optional<Photo> findPhotoByNamaFile(String namaFile);
}
