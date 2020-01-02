package com.example.eu_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableEurekaServer
public class EuServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EuServerApplication.class, args);
    }

}
