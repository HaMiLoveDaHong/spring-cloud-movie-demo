package com.zhw.cloud.fileupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author zhw
 * @since 2021/10/31
 */
@SpringBootApplication(scanBasePackages = "com.zhw.cloud.fileupload.*")
@EnableDiscoveryClient
public class FileUploadApp {

    public static void main(String[] args){
        SpringApplication.run(FileUploadApp.class,args);
    }

}
