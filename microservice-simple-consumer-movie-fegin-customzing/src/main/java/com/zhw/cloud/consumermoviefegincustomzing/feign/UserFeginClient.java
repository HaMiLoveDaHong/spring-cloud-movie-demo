package com.zhw.cloud.consumermoviefegincustomzing.feign;

import com.zhw.cloud.consumermoviefegincustomzing.config.FeignConfiguration;
import com.zhw.cloud.consumermoviefegincustomzing.vo.UserVo;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @Author zhw
 * @since 2021/11/5
 */
@FeignClient(name = "MICROSERVICE-SIMPLE-PROVIDER-USER",configuration = FeignConfiguration.class)
public interface UserFeginClient {

    @RequestLine(value = "GET /user/{id}")
    public UserVo findById(@Param("id") Long id);
}
