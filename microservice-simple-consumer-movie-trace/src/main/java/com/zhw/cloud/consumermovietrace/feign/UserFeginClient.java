package com.zhw.cloud.consumermovietrace.feign;

import com.zhw.cloud.consumermovietrace.vo.UserVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author zhw
 * @since 2021/11/5
 */
@FeignClient(name = "MICROSERVICE-SIMPLE-PROVIDER-USER-TRACE")
public interface UserFeginClient {

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public UserVo findById(@PathVariable("id") Long id);
}
