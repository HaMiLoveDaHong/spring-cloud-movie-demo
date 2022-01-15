package com.zhw.cloud.microservicehystrixturbinemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * @Author zhw
 * @since 2021/10/31
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbineStream
public class MicroserviceHystrixTurbineMqApp {

    public static void main(String[] args){
        SpringApplication.run(MicroserviceHystrixTurbineMqApp.class,args);
    }
}
