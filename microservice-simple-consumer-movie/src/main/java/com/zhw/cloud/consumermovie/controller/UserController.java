package com.zhw.cloud.consumermovie.controller;

import com.zhw.cloud.consumermovie.feign.UserFeginClient;
import com.zhw.cloud.consumermovie.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.zhw.cloud.consumermovie.common.StaticCommon.Url;

/**
 * @Author zhw
 * @since 2021/11/1
 */
@Slf4j
@RestController
@RequestMapping(Url+"user")
public class UserController {
    @Autowired
    RestTemplate restTemplate;
    @Value("${user.serviceUrl}")
    private String userServiceUrl;
    @Value("${user.appId}")
    private String appId;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private UserFeginClient userFeginClient;

    @GetMapping("/{id}")
    public UserVo findById(@PathVariable Long id){
//        UserVo findOne = restTemplate.getForObject("http://"+appId+"/user/"+id,UserVo.class);
        UserVo findOne = userFeginClient.findById(id);
        return findOne;
    }

    @GetMapping("/log-user-instance")
    public void logUserInstance(){
        ServiceInstance instance = loadBalancerClient.choose(appId);
        log.info("{}:{}:{}",instance.getServiceId(),instance.getHost(),instance.getPort());
    }
}
