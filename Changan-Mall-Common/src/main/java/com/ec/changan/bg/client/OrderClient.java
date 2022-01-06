package com.ec.changan.bg.client;

import com.ec.changan.bg.entities.CommonResult;
import com.ec.changan.bg.entities.Payment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "Changan-Mall-Order-Core") //目标微服务的名称
public interface OrderClient {
    //Feign接口中的方法要与目标服务中的Controller中的方法完全一致
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();
}
