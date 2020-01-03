package com.example.consul1.feign.impl;

import com.example.consul1.feign.UserFeign;
import org.springframework.stereotype.Component;

@Component
public class FeignImpl implements UserFeign {
    @Override
    public String show() {
        return "通过实现feign接口，处理降级";
    }
}
