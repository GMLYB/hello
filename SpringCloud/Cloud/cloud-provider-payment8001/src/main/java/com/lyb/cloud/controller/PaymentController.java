package com.lyb.cloud.controller;

import com.lyb.cloud.entities.CommonResult;
import com.lyb.cloud.entities.Payment;
import com.lyb.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverport;

    @PostMapping(value = "/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("createPayment：添加Payment返回结果-->" + result);

        if (result > 0){
            return new CommonResult(200,"添加Payment成功,serverport = " + serverport,result);
        }else {
            return new CommonResult(-1,"添加Payment失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult QueryPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("QueryPaymentById：查询Payment返回结果-->" + payment);
        if (payment != null){
            return new CommonResult(200,"查询Payment成功!,serverport = " + serverport,payment);
        }else {
            return new CommonResult(-1,"没有查询到对应数据",null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("PaymentController---> element: " + element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + " : " + instance.getHost() + " : " + instance.getPort() + " : " + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverport;
    }

}
