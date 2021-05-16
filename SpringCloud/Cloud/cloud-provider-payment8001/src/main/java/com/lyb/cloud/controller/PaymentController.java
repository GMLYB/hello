package com.lyb.cloud.controller;

import com.lyb.cloud.entities.CommonResult;
import com.lyb.cloud.entities.Payment;
import com.lyb.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("createPayment：添加Payment返回结果-->" + result);

        if (result > 0){
            return new CommonResult(200,"添加Payment成功",result);
        }else {
            return new CommonResult(-1,"添加Payment失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult QueryPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("QueryPaymentById：查询Payment返回结果-->" + payment);
        if (payment != null){
            return new CommonResult(200,"查询Payment成功!",payment);
        }else {
            return new CommonResult(-1,"没有查询到对应数据",null);
        }
    }

}
