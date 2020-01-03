package com.example.consul1;

import feign.Retryer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient//启用服务注册与发现
@EnableFeignClients//开启feign客户端
public class Consul1Application {
    @Bean //相当
    // 于xml中的bean标签，主要是用于调用当前方法获取指定对象
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    //重试机制
    @Bean
    public Retryer frignRetryer(){
        return new Retryer.Default();
    }
    public static void main(String[] args) {
        SpringApplication.run(Consul1Application.class, args);
    }

}
