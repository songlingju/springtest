package com.slj.springcloud.controller;

import com.slj.springcloud.entities.CommonResult;
import com.slj.springcloud.entities.Payment;
import com.slj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverport;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("******插入结果******"+result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,port"+serverport, result);
        }else{
            return new CommonResult(444, "插入数据库失败", null);
        }
    }


    @GetMapping("/payment/get/{id}")
    public CommonResult getPamentById(@PathVariable("id") Long id) {

        Payment pamentById = paymentService.getPamentById(id);
        log.info("******查询结果******"+pamentById);
        if (pamentById!=null) {
            return new CommonResult(200, "查询成功,port"+serverport, pamentById);
        }else{
            return new CommonResult(444, "查询失败，没有"+id+"的记录", null);
        }
    }


    @GetMapping("/payment/lb")
    public String getPaymentLb(){
        return serverport;
    }
}
