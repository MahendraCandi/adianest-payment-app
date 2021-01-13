package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.dao.FcmTokenDao;
import com.adianest.AdianestPaymentApp.model.FcmToken;
import com.adianest.AdianestPaymentApp.service.IFcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class FcmServiceImpl implements IFcmService {

    @Autowired
    private FcmTokenDao fcmTokenDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertToken(String id, String token) {

        FcmToken f = fcmTokenDao.findById(id).orElse(null);

        if (f == null) {
            f = new FcmToken();
            f.setId(id);
            f.setToken(token);
            f.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
            f.setModifiedDate(null);
        } else {
            f.setToken(token);
            f.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
        }

        fcmTokenDao.save(f);
        return true;
    }

    @Override
    public String getToken(String id) {
        FcmToken f = fcmTokenDao.findById(id).orElse(null);
        return f != null ? f.getToken() : null;
    }
}
