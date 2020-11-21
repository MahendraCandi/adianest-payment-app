/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.dao.KategoriTopUpDao;
import com.adianest.AdianestPaymentApp.model.KategoriTopUp;
import com.adianest.AdianestPaymentApp.service.IKategoriTopUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategoriTopUpImpl implements IKategoriTopUp {

    private static final Logger log = LogManager.getLogger(KategoriTopUpImpl.class);

    @Autowired
    private KategoriTopUpDao dao;

    @Override
    public boolean insertCategory(KategoriTopUp kategoriTopUp) {
        try{
            kategoriTopUp.setId(formatId(kategoriTopUp.getId()));
            dao.save(kategoriTopUp);

            return true;
        }catch (Exception e){
            log.error("Failed insert top up", e);
        }
        return false;
    }

    @Override
    public List<KategoriTopUp> getAllCategory() {
        return dao.findAll();
    }

    @Override
    public KategoriTopUp findCategoryById(String id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public KategoriTopUp updateCategory(KategoriTopUp kategoriTopUp) {
        KategoriTopUp obj = findCategoryById(kategoriTopUp.getId());
        if (obj != null) {
            try {
                obj.setDeskripsi(kategoriTopUp.getDeskripsi());
                insertCategory(obj);
                obj = dao.save(obj);

                return obj;
            } catch (Exception e){
                log.error("Failed update top up category", e);
            }

        }
        return null;
    }

    @Override
    public boolean deleteCategory(String id) {
        try {
            dao.deleteById(id);
            return true;
        } catch (Exception e){
            log.error("Failed delete top up category", e);
        }

        return false;
    }

    private String formatId (String id) {
        return id.replaceAll("\\s", "_").toUpperCase();
    }
}
