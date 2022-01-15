package com.zhw.cloud.consumermovieribboncustomizing;

import com.zhw.cloud.consumermovieribboncustomizing.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

/**
 * @Author zhw
 * @since 2021/11/1
 */
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {RibbonConfiguration.class})})
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumermovierIbbonCustomizingApp {
    public static void main(String[] args){
        SpringApplication.run(ConsumermovierIbbonCustomizingApp.class,args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
