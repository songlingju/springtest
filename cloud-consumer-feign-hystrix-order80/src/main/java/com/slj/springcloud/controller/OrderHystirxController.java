package com.slj.springcloud.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.slj.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystirxController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String rs = paymentHystrixService.paymentInfo_ok(id);
        return rs;
    };

    @GetMapping(value = "/consumer/payment/hystrix/nok/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_nokHandler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1000")
//    })
    public String paymentInfo_nok(@PathVariable("id") Integer id){
        String rs = paymentHystrixService.paymentInfo_nok(id);
        return rs;
    };
    public String paymentInfo_nokHandler(@PathVariable("id") Integer id){
        return "Order  hystrix   消费端80有误    de    熔断操作";
    };

}
