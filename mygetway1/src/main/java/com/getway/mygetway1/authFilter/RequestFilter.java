package com.getway.mygetway1.authFilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 局部过滤器，参数
 */
@Slf4j
public class RequestFilter implements GatewayFilter, Ordered {
    private static final String COUNT_Start_TIME="countStartTime";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("RequestFilter");
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            ServerHttpRequest request=exchange.getRequest();
            String path=request.getPath().toString();
            String addr=request.getRemoteAddress().getAddress().toString();
            MultiValueMap<String,String> queryParams=request.getQueryParams();
            log.info("path:"+path);
            log.info("addr:"+addr);
            log.info("params:"+queryParams.toString());
        }));
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
