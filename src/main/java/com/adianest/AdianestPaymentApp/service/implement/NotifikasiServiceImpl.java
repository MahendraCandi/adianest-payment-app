package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.dao.NotifikasiDao;
import com.adianest.AdianestPaymentApp.model.Notifikasi;
import com.adianest.AdianestPaymentApp.service.INotifikasi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotifikasiServiceImpl implements INotifikasi {

    private static final Logger logger = LogManager.getLogger(NotifikasiServiceImpl.class);

    @Autowired
    private NotifikasiDao notifikasiDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertNotifikasi(Notifikasi notifikasi) {
        Notifikasi n = notifikasiDao.save(notifikasi);
        return n.getId() != null;
    }

    @Override
    public boolean insertNotifikasi(String userId, String transaksiId, String transaksiKategori, String message, Timestamp tglTransaksi) {
        Notifikasi n = new Notifikasi();
        n.setUserId(userId);
        n.setTransaksiId(transaksiId);
        n.setStatus(0);
        n.setTransaksiKategori(transaksiKategori);
        n.setMessage(message);
        n.setTglTransaksi(tglTransaksi);

        return insertNotifikasi(n);
    }

    @Override
    public List<Notifikasi> getAllByUserId(String userId) {
        return notifikasiDao.findAllByUserId(userId).orElse(new ArrayList<>());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateAllNotifikasiByUserId(String userId) {
        List<Notifikasi> all = getAllByUserId(userId);
        boolean result = false;
        try{
            all.stream().forEach(p -> {
                p.setStatus(1);
                insertNotifikasi(p);
            });
            result = true;
        } catch(Exception e) {
            logger.error("update all notifikasi failed", e);
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateNotifikasiByIdTransaksi(String idTransaksi) {
        Notifikasi n = notifikasiDao.findByTransaksiId(idTransaksi).orElse(null);
        boolean result = false;

        if (n != null) {
            n.setStatus(1);
            notifikasiDao.save(n);
            result = true;
        }
        return result;
    }
}
