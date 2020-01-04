package com.getway.mygetway1.authFilter;

import org.apache.http.protocol.HTTP;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

/**
 * 全局过滤器，授权操作检验
 * Ordered：排序，过滤器的排序,运行顺序
 */
@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {

    //过滤器检验操作
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("AuthSignatureFilter-------1");
        //验证
        String token=exchange.getRequest().getQueryParams().getFirst("authToken");
        if(token==null||token.isEmpty()){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return  exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
