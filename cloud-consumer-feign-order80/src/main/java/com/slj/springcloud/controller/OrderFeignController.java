package com.slj.springcloud.controller;

import com.slj.springcloud.entities.CommonResult;
import com.slj.springcloud.entities.Payment;
import com.slj.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPamentById(@PathVariable("id") Long id){
        return paymentFeignService.getPamentById(id);
    }
}
