package com.lyb.cloud.service;

import com.lyb.cloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


public interface PaymentService {
    //写
    public int create(Payment payment);

    //读
    public Payment getPaymentById(@Param("id")Long id);

}
