package org.action.gateway.limit;


import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class SentinelConfiguration {

    @PostConstruct
    public void initGatewayBlockHandler() {
        GatewayCallbackManager.setBlockHandler((serverWebExchange, ex) -> ServerResponse.ok().body(Mono.just("限流啦,请求太频繁"), String.class));
    }
}
