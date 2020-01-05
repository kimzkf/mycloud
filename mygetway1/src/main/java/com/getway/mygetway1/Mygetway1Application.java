package com.getway.mygetway1;

import com.getway.mygetway1.authFilter.RequestFilter;
import com.getway.mygetway1.authFilter.TimeFilter;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient//启用服务注册与发现
@EnableSwagger2Doc//开启swagger
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
    //添加文档来源
    @Component
    @Primary
    class DocumentationConfig implements SwaggerResourcesProvider{
        @overide
        public List<SwaggerResource> get(){
            List resources=new ArrayList();
            resources.add(swaggerResource("会员服务"，"/api-member/v2/api-docs","0.1"));
            resources.add(swaggerResource("订单服务"，"/api-order/v2/api-docs","0.1"));
            return resources;
        }
        private SwaggerResource swaggerResource(Strign name,String location,String version){
            SwaggerResource swaggerResource=new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
        }
    }

}
