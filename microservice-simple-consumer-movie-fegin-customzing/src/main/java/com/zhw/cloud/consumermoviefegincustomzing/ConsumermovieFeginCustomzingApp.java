package com.zhw.cloud.consumermoviefegincustomzing;

import com.zhw.cloud.consumermoviefegincustomzing.config.FeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

/**
 * @Author zhw
 * @since 2021/11/1
 */
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {FeignConfiguration.class})})
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumermovieFeginCustomzingApp {
    public static void main(String[] args){
        SpringApplication.run(ConsumermovieFeginCustomzingApp.class,args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
