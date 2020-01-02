package com.example.eu_client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RestController;

//默认实现ribbon客户端负载均衡

@RestController
@SpringBootApplication
@EnableEurekaClient//启用eureka客户端
public class EuClientApplication {

    @Bean //相当于xml中的bean标签，主要是用于调用当前方法获取指定对象
    @LoadBalanced //使用ribbon
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(EuClientApplication.class, args);
    }
    /**
     * 获取端口号
     */
    @Value("${server.port}")
    String port;

    /**
     * 定义一个简单接口
     *
     * @param name
     * @return
     */
    @GetMapping("/hi/{name}")
    public String home(@PathVariable String name) {
        return "hi " + name + ",I am from port :" + port;
    }

}
