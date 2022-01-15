package com.zhw.cloud.microservicegatewayzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author zhw
 * @since 2021/10/31
 */
@SpringBootApplication(scanBasePackages = "com.zhw.cloud.microservicegatewayzuul.*")
@EnableDiscoveryClient
@EnableZuulProxy
public class MicroserviceGatewayZuulApp {

    public static void main(String[] args){
        SpringApplication.run(MicroserviceGatewayZuulApp.class,args);
    }
}
