package com.zhw.cloud.consumermoviefegincustomzing.config;

import feign.Contract;
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

    /**
     * 将契约改为feign原生的默认契约。这样就可以使用feign自带的注解了。
     * @return 默认的feign契约
     */
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }
}
