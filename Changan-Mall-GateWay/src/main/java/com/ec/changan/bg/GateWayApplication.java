package com.ec.changan.bg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
//Discovery服务发现
@EnableDiscoveryClient

public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }
}
