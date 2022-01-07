package com.ec.changan.bg.controller;

import com.ec.changan.bg.client.OrderClient;
import com.ec.changan.bg.entities.CommonResult;
import com.ec.changan.bg.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

	@Value("${server.port}")
	private String serverPort; //获取配置文件中的值
	@Resource
	private DiscoveryClient discoveryClient;

	@Resource
	private OrderClient orderClient;

	@GetMapping(value = "/payment/get/{id}")
	public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
		log.info("Fegin 调用原子服务");
		return orderClient.getPaymentById(id);
	}

	@GetMapping(value = "/payment/lb")
	public String getPaymentLB() {
		return serverPort;
	}

	//OpenFeign超时控制演示
	//OpenFeign默认等待1秒钟，超过则报错
	@GetMapping(value = "/payment/feign/timeout")
	public String paymentFeignTimeout() {
		//暂停几秒钟线程
		try{
			TimeUnit.SECONDS.sleep(3);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		return serverPort;
	}

	@GetMapping(value = "/payment/zipkin")
	public String paymentZipkin() {
		return "链路跟踪zipkin访问成功";
	}

}
