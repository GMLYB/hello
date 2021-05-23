package com.lyb.cloud.dao;

import com.lyb.cloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentMapper {

    //写
    int create(Payment payment);

    //读
    Payment getPaymentById(@Param("id") Long id);
}
