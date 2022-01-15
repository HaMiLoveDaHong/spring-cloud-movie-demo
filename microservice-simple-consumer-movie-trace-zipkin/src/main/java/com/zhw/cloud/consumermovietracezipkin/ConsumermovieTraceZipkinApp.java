package com.zhw.cloud.consumermovietracezipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 整合 spring cloud sleuth 分布式跟踪
 * @Author zhw
 * @since 2021/11/1
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumermovieTraceZipkinApp {
    public static void main(String[] args){
        SpringApplication.run(ConsumermovieTraceZipkinApp.class,args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
