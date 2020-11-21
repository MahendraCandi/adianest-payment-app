/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.model.KategoriTopUp;

import java.util.List;

public interface IKategoriTopUp {

    boolean insertCategory(KategoriTopUp kategoriTopUp);

    List<KategoriTopUp> getAllCategory();

    KategoriTopUp findCategoryById(String id);

    KategoriTopUp updateCategory(KategoriTopUp kategoriTopUp);

    boolean deleteCategory(String id);


}
