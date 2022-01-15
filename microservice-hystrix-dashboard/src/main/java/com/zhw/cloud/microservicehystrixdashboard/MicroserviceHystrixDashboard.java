package com.zhw.cloud.microservicehystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Author zhw
 * @since 2021/10/31
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
public class MicroserviceHystrixDashboard {

    public static void main(String[] args){
        SpringApplication.run(MicroserviceHystrixDashboard.class,args);
    }
}
