package com.ec.changan.bg.dao;

import com.ec.changan.bg.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
	public int create(Payment payment); //增

	public Payment getPaymentById(@Param("id") Long id); //查
}
