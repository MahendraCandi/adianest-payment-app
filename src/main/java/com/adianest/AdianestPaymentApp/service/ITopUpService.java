/*
 * Copyright (c) 2020. <a href="https://github.com/MahendraCandi">MahendraCandi </a>.
 */

package com.adianest.AdianestPaymentApp.service;

import com.adianest.AdianestPaymentApp.dto.TopUpDto;

public interface ITopUpService {

    boolean insertTopUp(TopUpDto dto);

    TopUpDto insertConfirmTopUp(TopUpDto dto);

    TopUpDto getConfirmTopUp(TopUpDto dto);

}
