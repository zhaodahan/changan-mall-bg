package com.ec.changan.bg.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ec.changan.bg.client.OrderClient;
import com.ec.changan.bg.controller.myhandler.CustomerBlockHandler;
import com.ec.changan.bg.entities.CommonResult;
import com.ec.changan.bg.entities.dto.PaymentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Api(tags = "测试swagger3接口文档")
@RestController
//支持nacos动态刷新配置
@RefreshScope
@Slf4j
public class PaymentController {

	@Value("${server.port}")
	private String serverPort; //获取配置文件中的值
	@Resource
	private DiscoveryClient discoveryClient;

	@Resource
	private OrderClient orderClient;

	@ApiOperation(value = "获取对应id的信息", notes = "测试使用")
	@GetMapping(value = "/payment/get/{id}")
	public CommonResult<PaymentDTO> getPaymentById(@ApiParam(value = "id",required=true) @PathVariable("id") Long id) {
		log.info("Fegin 调用原子服务");
		return orderClient.getPaymentById(id);
	}

	@ApiOperation(value = "获取服务端口", notes = "测试使用")
	@GetMapping(value = "/payment/lb")
	public String getPaymentLB(@ApiParam(value = "随意参数",required=false) String param) {
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


	@GetMapping("/rateLimit/customerBlockHandler")
	@SentinelResource(value = "customerBlockHandler",
			blockHandlerClass = CustomerBlockHandler.class,
			blockHandler = "handlerException2")
	public CommonResult customerBlockHandler() {
		return new CommonResult(200,"客户自定义",new PaymentDTO(2020L,"serial003"));
	}

}
