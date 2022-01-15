package com.zhw.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author zhw
 * @since 2021/11/1
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApp {

    public static void main(String[] args){
        SpringApplication.run(EurekaApp.class,args);
    }
}
