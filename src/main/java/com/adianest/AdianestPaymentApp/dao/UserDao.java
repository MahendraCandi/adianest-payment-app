/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.dao;

import com.adianest.AdianestPaymentApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, String> {

    @Query(
            value = "SELECT " +
                    "   A.id AS USER_ID, " +
                    "   A.no_telpon AS NO_TELPON, " +
                    "   A.email AS EMAIL, " +
                    "   A.nama_lengkap AS NAMA_LENGKAP, " +
                    "   C.id AS AUTHORITIES_ID, " +
                    "   C.nama AS AUTHORITIES_NAME " +
                    "FROM " +
                    "   USER A " +
                    "LEFT JOIN USER_AUTHORITIES B ON A.id = B.user_id " +
                    "JOIN AUTHORITIES C ON B.authorities_id = C.id " +
                    "WHERE " +
                    "   A.id = :id",
            nativeQuery = true
    )
    List<Object[]> findUserAndAuthoritiesByIdUser(@Param("id") String id);

    @Query(
            value = "SELECT " +
                    "   A.id AS USER_ID, " +
                    "   A.no_telpon AS NO_TELPON, " +
                    "   A.email AS EMAIL, " +
                    "   A.nama_lengkap AS NAMA_LENGKAP, " +
                    "   C.id AS AUTHORITIES_ID, " +
                    "   C.nama AS AUTHORITIES_NAME, " +
                    "   A.password AS PASSWORD " +
                    "FROM " +
                    "   USER A " +
                    "LEFT JOIN USER_AUTHORITIES B ON A.id = B.user_id " +
                    "JOIN AUTHORITIES C ON B.authorities_id = C.id " +
                    "WHERE " +
                    "   A.no_telpon = :no_telpon",
            nativeQuery = true
    )
    List<Object[]> findUserAndAuthoritiesByNoTelpon(@Param("no_telpon") String noTelpon);

    @Query(
            value = "SELECT " +
                    "   A.id AS USER_ID, " +
                    "   A.no_telpon AS NO_TELPON, " +
                    "   A.email AS EMAIL, " +
                    "   A.nama_lengkap AS NAMA_LENGKAP, " +
                    "   C.id AS AUTHORITIES_ID, " +
                    "   C.nama AS AUTHORITIES_NAME " +
                    "FROM " +
                    "   USER A " +
                    "LEFT JOIN USER_AUTHORITIES B ON A.id = B.user_id " +
                    "JOIN AUTHORITIES C ON B.authorities_id = C.id ",
            nativeQuery = true
    )
    List<Object[]> findAllUsersWithAuthorities();

    Optional<User> findTopByOrderByIdDesc();
}
