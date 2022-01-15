package com.zhw.cloud.providerusertracezipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author zhw
 * @since 2021/10/31
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderUserTraceZipkinApp {

    public static void main(String[] args){
        SpringApplication.run(ProviderUserTraceZipkinApp.class,args);
    }

}
