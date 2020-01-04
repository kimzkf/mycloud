package com.getway.mygetway1;

import com.getway.mygetway1.authFilter.RequestFilter;
import com.getway.mygetway1.authFilter.TimeFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient//启用服务注册与发现
public class Mygetway1Application {

    public static void main(String[] args) {
        SpringApplication.run(Mygetway1Application.class, args);
    }

    //路由配置
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                //基于BAIDU-SERVER代理，可以多个
                //.route(r->r.path("/api-order/**").filters(f->f.stripPrefix(1)).uri("lb://consul2"))
                .route(r->r.path("/api-order/**").filters(f->f.stripPrefix(1).filter(new TimeFilter()).filter(new RequestFilter())).uri("lb://consul2"))//配置局部过滤器
                //.route(r->r.path("/api-member/**").filters(f->f.stripPrefix(1)).uri("lb://consul3"))
                .build();
    }
}
