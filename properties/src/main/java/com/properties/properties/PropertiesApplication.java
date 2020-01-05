package com.properties.properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({StudentConfig.class})
public class PropertiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertiesApplication.class, args);
    }

}
