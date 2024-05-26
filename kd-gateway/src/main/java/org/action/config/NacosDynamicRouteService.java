//package org.action.config;
//import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.common.collect.Lists;
//import java.util.ArrayList;
//import java.util.List;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.ApplicationEventPublisherAware;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
///**
// * @author gzw
// * @description：
// * @since：2024/5/19 19:33
// */
//@Component
//@SuppressWarnings("all")
//public class NacosDynamicRouteService implements ApplicationEventPublisherAware {
//
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(NacosDynamicRouteService.class);
//
//
//    private RouteDefinitionWriter routeDefinitionWriter;
//
//    private ApplicationEventPublisher applicationEventPublisher;
//
//    /** 路由id */
//    private static List<String> routeIds = Lists.newArrayList();
//
//    @Override
//    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//            this.applicationEventPublisher = applicationEventPublisher;
//    }
//
//
//    /**
//     * 监听nacos路由配置，动态改变路由
//     * @param configInfo
//     */
//    @NacosConfigListener(dataId = "routes", groupId = "gateway-server")
//    public void routeConfigListener(String configInfo) {
//        clearRoute();
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            List<RouteDefinition> gatewayRouteDefinitions = mapper.readValue(configInfo, ArrayList.class);
//            for (RouteDefinition routeDefinition : gatewayRouteDefinitions) {
//                addRoute(routeDefinition);
//            }
//            publish();
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//        }
//
//    }
//
//    /**
//     * 添加路由
//     *
//     * @param definition
//     */
//    private void addRoute(RouteDefinition definition) {
//        try {
//            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
//            routeIds.add(definition.getId());
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//        }
//    }
//
//    /**
//     * 发布路由、使路由生效
//     */
//    private void publish() {
//        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this.routeDefinitionWriter));
//    }
//
//
//    /**
//     * 清空路由
//     */
//    private void clearRoute() {
//        for (String id : routeIds) {
//            routeDefinitionWriter.delete(Mono.just(id)).subscribe();
//        }
//        routeIds.clear();
//    }
//}
