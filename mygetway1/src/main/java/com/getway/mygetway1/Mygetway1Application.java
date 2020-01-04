package com.getway.mygetway1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient//启用服务注册与发现
public class Mygetway1Application {

    public static void main(String[] args) {
        SpringApplication.run(Mygetway1Application.class, args);
    }

}
