package com.slj.springcloud.controller;

import com.slj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverport;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String rs = paymentService.paymentInfo_ok(id);
        log.info("-----ok-----"+rs);
        return rs;
    }

    @GetMapping(value = "/payment/hystrix/nok/{id}")
    public String paymentInfo_nok(@PathVariable("id") Integer id){
        String rs = paymentService.paymentInfo_nok(id);
        log.info("-----no--ok-----"+rs);
        return rs;
    }
    //---------服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("****result: "+result);
        return result;
    }


}
