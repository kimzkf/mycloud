package com.example.consul2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${name:default}")//默认值
    private String name;

   @RequestMapping("/order")
    public String getOrder() throws InterruptedException {
       System.out.println("进入consul2");
       //Thread.sleep(100000);
         return "222";
    }
    /**
     * 获取配置服务器的配置
     * @return
     */
    @RequestMapping("/config")
    public String getConfig(){
        return  name;
    }
}
