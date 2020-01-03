package com.example.consul1.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("consul2")//生产者的服务名
public interface UserFeign {
    @RequestMapping("/order")//请求生产者的地址
    String show();
}
