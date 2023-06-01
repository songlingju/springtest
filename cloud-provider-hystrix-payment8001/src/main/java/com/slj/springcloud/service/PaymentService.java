package com.slj.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {


    //------------------------------------服务降级--------------------------------------------------
    public String paymentInfo_ok(Integer id){
        return "线程池："+Thread.currentThread().getName()+"     paymentInfo_ok:"+id +"\t" +"哈哈哈------";
    }
    @HystrixCommand(fallbackMethod = "paymentInfo_nokHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
    public String paymentInfo_nok(Integer id){
        int timenum=3;
        try {
            //暂停3秒钟
            TimeUnit.SECONDS.sleep(timenum);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"     paymentInfo_nok:"+id +"\t" +"不不不不------耗时："+timenum+"s";
    }
    public String paymentInfo_nokHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+"     paymentInfo_nokHandler:"+id +"\t"+"-----.-.-.-------";
    }

    //------------------------------------服务熔断--------------------------------------------------
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }




}
