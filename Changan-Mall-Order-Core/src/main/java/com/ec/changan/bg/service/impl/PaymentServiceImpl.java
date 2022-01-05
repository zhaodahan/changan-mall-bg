package com.ec.changan.bg.service.impl;


import com.ec.changan.bg.dao.PaymentDao;
import com.ec.changan.bg.service.PaymentService;
import com.ec.changan.bg.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Resource
	private PaymentDao paymentDao;

	public int create(Payment payment) {
		return paymentDao.create(payment);
	}

	public Payment getPaymentById(@Param("id") Long id) {
		return paymentDao.getPaymentById(id);
	}
}
