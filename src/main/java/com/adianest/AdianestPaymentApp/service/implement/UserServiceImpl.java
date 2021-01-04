/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service.implement;

import com.adianest.AdianestPaymentApp.config.PasswordConfig;
import com.adianest.AdianestPaymentApp.dao.PhotoDao;
import com.adianest.AdianestPaymentApp.dao.UserDao;
import com.adianest.AdianestPaymentApp.dto.UserDto;
import com.adianest.AdianestPaymentApp.model.Photo;
import com.adianest.AdianestPaymentApp.model.Saldo;
import com.adianest.AdianestPaymentApp.model.User;
import com.adianest.AdianestPaymentApp.model.UserAuthorities;
import com.adianest.AdianestPaymentApp.service.ISaldo;
import com.adianest.AdianestPaymentApp.service.IUserAuthorities;
import com.adianest.AdianestPaymentApp.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PhotoDao photoDao;

    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private IUserAuthorities userAuthoritiesSvc;

    @Autowired
    private ISaldo saldoService;

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertUser(UserDto userDto) {
        boolean success = false;
        try {

            User user = new User();
            user.setId(createUserId());
            user.setPassword(passwordConfig.passwordEncoder().encode(userDto.getPasswordUser()));
            user.setNoTelpon(userDto.getNoTelpon());
            user.setEmail(userDto.getEmailUser());
            user.setNamaLengkap(userDto.getNameUser());
//            user.setPhotoId();
            userDao.save(user);

            UserAuthorities userAuthorities = new UserAuthorities(user.getId(), userDto.getIdAuthorities());
            if (!userAuthoritiesSvc.insertUserAuthorities(userAuthorities))
                throw new NullPointerException("failed insert user authorities");

            Saldo saldo = saldoService.insertFirstSaldo(user.getId());
            if (saldo.getId() == null) throw new NullPointerException("Failed insert first saldo");

            success = true;
        } catch (Exception e){
            log.error("Failed insert new user", e);
        }

        return success;
    }



    @Override
    public UserDto getOneUserById(String id) {
        List<Object[]> objects = userDao.findUserAndAuthoritiesByIdUser(id);
        UserDto userDto = null;
        if (!objects.isEmpty()) {
            for (Object[] obj : objects) {
                userDto = new UserDto();
                userDto.setIdUser(obj[0].toString());
                userDto.setNoTelpon(obj[1].toString());
                userDto.setEmailUser(obj[2].toString());
                userDto.setNameUser(obj[3].toString());
                userDto.setIdAuthorities(Integer.valueOf(obj[4].toString()));
                userDto.setNameAuthorities(obj[5].toString());
            }
        }

        return userDto;
    }

    @Override
    public UserDto getOneUserByNoTelpon(String noTelpon) {
        List<Object[]> objects = userDao.findUserAndAuthoritiesByNoTelpon(noTelpon);
        UserDto userDto = null;
        if (!objects.isEmpty()) {
            for (Object[] obj : objects) {
                userDto = new UserDto();
                userDto.setIdUser(obj[0].toString());
                userDto.setNoTelpon(obj[1].toString());
                userDto.setEmailUser(obj[2].toString());
                userDto.setNameUser(obj[3].toString());
                userDto.setIdAuthorities(Integer.valueOf(obj[4].toString()));
                userDto.setNameAuthorities(obj[5].toString());
                userDto.setPasswordUser(obj[6].toString());
                userDto.setIdPhoto(obj[7].toString());
            }
        }

        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<Object[]> objects = userDao.findAllUsersWithAuthorities();
        if (!objects.isEmpty()) {
            List<UserDto> dtos = new ArrayList<>();
            for (Object[] obj : objects) {
                UserDto userDto = new UserDto();
                userDto.setIdUser(obj[0].toString());
                userDto.setNoTelpon(obj[1].toString());
                userDto.setEmailUser(obj[2].toString());
                userDto.setNameUser(obj[3].toString());
                userDto.setIdAuthorities(Integer.valueOf(obj[4].toString()));
                userDto.setNameAuthorities(obj[5].toString());

                dtos.add(userDto);
            }

            return dtos;
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUser(String id) {
        try{
            userDao.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Failed delete user", e);
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserDto updateUser(UserDto userDto) {

        try{
            User user = userDao.findById(userDto.getIdUser()).orElseThrow(NullPointerException::new);


            if (userDto.getPathPhoto() != null && userDto.getNamaPhoto() !=null) {
                Photo p;
                if (user.getPhotoId() == null) {
                    p = new Photo();
                } else {
                    p = photoDao.findById(user.getPhotoId()).get();
                }

                p.setNamaFile(userDto.getNamaPhoto());
                p.setPathFile(userDto.getPathPhoto());

                p = photoDao.save(p);

                user.setPhotoId(p.getId());
            }

            user.setNoTelpon(userDto.getNoTelpon());
            user.setEmail(userDto.getEmailUser());
            user.setNamaLengkap(userDto.getNameUser());

            userDao.save(user);

            UserAuthorities userAuthorities = new UserAuthorities(user.getId(), userDto.getIdAuthorities());
            if (!userAuthoritiesSvc.insertUserAuthorities(userAuthorities)) throw new NullPointerException("Null");

            return userDto;
        }catch(Exception e){
            log.error("Failed update user", e);
        }

        return userDto;
    }

    private String createUserId() {
        User u = userDao.findTopByOrderByIdDesc().orElse(null);
        String userId = "user-";
        if (u == null) {
            userId += "001";
        } else {
            Integer lastId = Integer.valueOf(u.getId().substring(5));
            lastId++;
            userId += String.format("%03d", lastId);

        }
        return userId;
    }
}
