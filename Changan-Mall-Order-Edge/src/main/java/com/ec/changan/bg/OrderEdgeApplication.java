package com.ec.changan.bg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
//Discovery服务发现
@EnableDiscoveryClient
public class OrderEdgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderEdgeApplication.class, args);
    }
}
