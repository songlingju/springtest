package com.slj.springcloud.service;

import com.slj.springcloud.entities.CommonResult;
import com.slj.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
//    CommonResult<Payment> getPamentById(@Param("id") Long id);
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPamentById(@PathVariable("id") Long id);

}
