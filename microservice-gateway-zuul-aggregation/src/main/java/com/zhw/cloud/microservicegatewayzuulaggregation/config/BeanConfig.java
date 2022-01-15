package com.zhw.cloud.microservicegatewayzuulaggregation.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Author zhw
 * @since 2022/1/12
 */
@Component
public class BeanConfig {

    @Bean
    public PatternServiceRouteMapper serviceRouteMapper(){
        //调用构造函数 PatternServiceRouteMapper(String servicePattern, String routePattern)
        //servicePattern  指定微服务正则
        //routePattern 指定路由正则
        //可以指定 microservice-simple-provider-user-v3 这个服务，映射到/v1/microservice-simple-provider-user/** 这个路径
        return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)","${version}/${name}");
    }

    /**
     * 开启负载均衡的功能
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}