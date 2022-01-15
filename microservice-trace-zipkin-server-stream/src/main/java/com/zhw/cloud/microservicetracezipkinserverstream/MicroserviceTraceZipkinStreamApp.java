package com.zhw.cloud.microservicetracezipkinserverstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import zipkin.server.EnableZipkinServer;

/**
 * 访问http://localhost:9411 界面
 * 查询条件的含义：
 * Service Name 表示服务名称，也就是各个微服务 spring.application.name 的值
 * 第二列表示span 的名称，all 表示所有span ，也可以选择指定的span
 * Duration 表示持续时间，即span从创建到关闭所经历的时间
 * Annotations Auery ，用于自定义查询条件
 * @Author zhw
 * @since 2021/10/31
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinStreamServer
public class MicroserviceTraceZipkinStreamApp {

    public static void main(String[] args){
        SpringApplication.run(MicroserviceTraceZipkinStreamApp.class,args);
    }
}
