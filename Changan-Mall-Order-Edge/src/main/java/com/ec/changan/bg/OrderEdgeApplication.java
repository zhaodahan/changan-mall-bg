package com.ec.changan.bg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
//Discovery服务发现
@EnableDiscoveryClient
//激活并开启Fegin
@EnableFeignClients
public class OrderEdgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderEdgeApplication.class, args);
    }
}
