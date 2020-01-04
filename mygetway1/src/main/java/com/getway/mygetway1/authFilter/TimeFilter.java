package com.getway.mygetway1.authFilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 局部过滤器,计时过滤器
 */

@Slf4j
public class TimeFilter implements GatewayFilter, Ordered {
    private static final String COUNT_Start_TIME="countStartTime";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //------先执行------
        exchange.getAttributes().put(COUNT_Start_TIME,System.currentTimeMillis());

        //------后执行-------
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime=exchange.getAttribute(COUNT_Start_TIME);
            Long endTime=(System.currentTimeMillis()-startTime);
            if(startTime!=null){
                log.info(exchange.getRequest().getURI().toString()+":"+endTime+"ms");
            }
        }));
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
