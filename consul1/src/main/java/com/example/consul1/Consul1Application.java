package com.example.consul1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Consul1Application {

    public static void main(String[] args) {
        SpringApplication.run(Consul1Application.class, args);
    }

}
