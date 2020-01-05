package com.example.consul1;


import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient//启用服务注册与发现
@EnableFeignClients//开启feign客户端
@EnableHystrix//开启熔断，下面的frignRetryer就不需要了
@EnableHystrixDashboard//可视化面板http://127.0.0.1:8504/hystrix
@EnableSwagger2Doc //开启swagger生成文档
public class Consul1Application {
    @Bean //相当
    // 于xml中的bean标签，主要是用于调用当前方法获取指定对象
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    //重试机制
   // @Bean
    //public Retryer frignRetryer(){
    //    return new Retryer.Default();
   // }
    public static void main(String[] args) {
        SpringApplication.run(Consul1Application.class, args);
    }

}
