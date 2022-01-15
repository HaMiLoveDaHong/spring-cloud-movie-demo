package com.zhw.cloud.consumermovieribboncustomizing.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 该类为Ribbon 的配置类
 * 注意：该类不应该在主应用程序上下文的@ComponentScan中
 * @Author zhw
 * @since 2021/11/5
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule(){
        /**
         * 负载均衡规则，改为随机
         */
        return new RandomRule();
    }
}
