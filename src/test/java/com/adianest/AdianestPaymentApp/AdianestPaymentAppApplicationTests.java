/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp;

import com.adianest.AdianestPaymentApp.dao.UserDao;
import com.adianest.AdianestPaymentApp.model.Saldo;
import com.adianest.AdianestPaymentApp.model.User;
import com.adianest.AdianestPaymentApp.service.ISaldo;
import com.adianest.AdianestPaymentApp.service.ITransaksiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class AdianestPaymentAppApplicationTests {

	@Autowired
	UserDao userDao;

	@Autowired
	ITransaksiService transaksiService;

	@Autowired
	ISaldo saldoService;

	@Test
	void contextLoads() {
//		User user = userDao.findTopByOrderByIdDesc().orElse(null);
//		System.out.println(user.getId());

//		String x = "user-001".substring(5);
//		System.out.println(Integer.valueOf(x));
//		Integer xx = Integer.valueOf(x);
//
//		xx++;

//		System.out.println(String.format("%03d", xx));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyHHmm");
        String date = LocalDateTime.now().format(dtf);

        System.out.println(date);

        String idTransaksi = "PULSA" + date + "001";
        String lastRow = idTransaksi.substring(idTransaksi.length() - 3);
        String dateTimePrevious = idTransaksi.substring(idTransaksi.length() - 13, idTransaksi.length() - 3);
        System.out.println("dateTimePrevious: " + dateTimePrevious);
        System.out.println(lastRow);

        System.out.println(String.format("%03d", 30));
//
//        String idTrans = transaksiService.getFormatIdTransaksi("TOP_UP");
//        System.out.println(idTrans);

//        System.out.println("082178901234".substring(1));
//
//        Saldo saldo = saldoService.getEndingBalanceByUserId("user-001");
//        try {
//            System.out.println(new ObjectMapper().writeValueAsString(saldo));
//            BigDecimal amount = new BigDecimal("-100000000").setScale(2, BigDecimal.ROUND_CEILING);
//            BigDecimal amountFirst = new BigDecimal("100000000").setScale(2, BigDecimal.ROUND_CEILING);
//            System.out.println(amountFirst.add(amount));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

}
