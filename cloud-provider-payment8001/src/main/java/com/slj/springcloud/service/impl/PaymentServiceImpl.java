package com.slj.springcloud.service.impl;

import com.slj.springcloud.dao.PaymentDao;
import com.slj.springcloud.entities.Payment;
import com.slj.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPamentById(Long id) {
        return paymentDao.getPamentById(id);
    }
}
