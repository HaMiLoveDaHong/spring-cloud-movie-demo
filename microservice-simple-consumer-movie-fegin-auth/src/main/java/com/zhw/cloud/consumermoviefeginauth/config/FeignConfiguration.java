package com.zhw.cloud.consumermoviefeginauth.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 该类为Fegin的配置类
 * 注意：该类不应该在主应用程序上下文的@ComponentScan中
 * @Author zhw
 * @since 2021/11/6
 */
@Configuration
public class FeignConfiguration {

    @Bean
    Logger.Level feginLoggerLevel(){
        return Logger.Level.FULL;
    }

    /**
     * Feign请求拦截器
     * @author yinjihuan
     * @create 2017-11-10 17:25
     **/
    public static class FeignBasicAuthRequestInterceptor implements RequestInterceptor {
        public FeignBasicAuthRequestInterceptor() {
        }
        @Override
        public void apply(RequestTemplate template) {
            template.header("X-Authorization-access_token", "token123456");
        }
    }
}
