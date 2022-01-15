package com.zhw.cloud.consumermoviefeginauth.feign;

import com.zhw.cloud.consumermoviefeginauth.config.FeignConfiguration;
import com.zhw.cloud.consumermoviefeginauth.vo.UserVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author zhw
 * @since 2021/11/5
 */
//@FeignClient(name = "microservice-simple-provider-user-auth",configuration = FeignConfiguration.class)
public interface UserFeginClient {

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public UserVo findById(@PathVariable("id") Long id);

    @RequestMapping(value = "/user/get",method = RequestMethod.GET)
    public String get(@RequestParam Map<String,Object> map);

    @RequestMapping(value = "/user/post",method = RequestMethod.POST)
    public String post(@RequestBody UserVo user);
}
