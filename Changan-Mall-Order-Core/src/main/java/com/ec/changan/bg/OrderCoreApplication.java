package com.ec.changan.bg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient //Discovery服务发现
public class OrderCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderCoreApplication.class, args);
    }
}
