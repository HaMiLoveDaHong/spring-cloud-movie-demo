package com.zhw.cloud.consumermoviefeginauth.feign;

import com.zhw.cloud.consumermoviefeginauth.config.FeignConfiguration;
import com.zhw.cloud.consumermoviefeginauth.config.FeignConfigurationByRole;
import com.zhw.cloud.consumermoviefeginauth.vo.UserVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author zhw
 * @since 2021/11/5
 */
//@FeignClient(name = "microservice-simple-provider-user-auth",configuration = FeignConfigurationByRole.class)
public interface UserFeginClientByAdmin {

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public UserVo findById(@PathVariable("id") Long id);
}
