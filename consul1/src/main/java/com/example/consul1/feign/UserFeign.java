package com.example.consul1.feign;

import com.example.consul1.feign.impl.FeignImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//value生产者的服务名,fallback降级处理
@FeignClient(value = "consul2",fallback = FeignImpl.class)
public interface UserFeign {
    @RequestMapping("/order")//请求生产者的地址
    String show();
}
