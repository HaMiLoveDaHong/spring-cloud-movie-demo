package com.zhw.cloud.consumermoviefeginauth;

import com.zhw.cloud.consumermoviefeginauth.config.FeignConfiguration;
import com.zhw.cloud.consumermoviefeginauth.config.FeignConfigurationByRole;
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
//@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {FeignConfiguration.class, FeignConfigurationByRole.class})})
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumermovieFeginAuthApp {
    public static void main(String[] args){
        SpringApplication.run(ConsumermovieFeginAuthApp.class,args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
