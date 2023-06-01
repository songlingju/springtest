package com.slj.springcloud.service;

import com.slj.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int create(Payment payment);

    public Payment getPamentById(@Param("id")Long id);
}
