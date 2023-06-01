package com.slj.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_ok(Integer id) {
        return "--PaymentFallbackService  fall back--ok";
    }

    @Override
    public String paymentInfo_nok(Integer id) {
        return "--PaymentFallbackService  fall back--nok";
    }
}
