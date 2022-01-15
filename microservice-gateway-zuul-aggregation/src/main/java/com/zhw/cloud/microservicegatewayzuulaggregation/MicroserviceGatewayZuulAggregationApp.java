package com.zhw.cloud.microservicegatewayzuulaggregation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 聚合服务
 * @Author zhw
 * @since 2021/10/31
 */
@SpringBootApplication(scanBasePackages = "com.zhw.cloud.microservicegatewayzuulaggregation.*")
@EnableDiscoveryClient
@EnableZuulProxy
public class MicroserviceGatewayZuulAggregationApp {

    public static void main(String[] args){
        SpringApplication.run(MicroserviceGatewayZuulAggregationApp.class,args);
    }


}
