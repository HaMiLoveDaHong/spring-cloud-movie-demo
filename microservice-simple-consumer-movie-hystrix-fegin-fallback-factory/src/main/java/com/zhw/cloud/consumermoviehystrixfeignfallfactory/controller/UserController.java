package com.zhw.cloud.consumermoviehystrixfeignfallfactory.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zhw.cloud.consumermoviehystrixfeignfallfactory.feign.UserFeginClient;
import com.zhw.cloud.consumermoviehystrixfeignfallfactory.vo.UserVo;
import com.zhw.cloud.consumermoviehystrixfeignfallfactory.common.StaticCommon;
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

/**
 * @Author zhw
 * @since 2021/11/1
 */
@Slf4j
@RestController
@RequestMapping(StaticCommon.Url+"user")
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


    @HystrixCommand(fallbackMethod = "findByIdfAllback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "10000")
    },threadPoolProperties = {
            @HystrixProperty(name = "coreSize",value = "1"),
            @HystrixProperty(name = "maxQueueSize",value = "10")
    })
    @GetMapping("/{id}")
    public UserVo findById(@PathVariable Long id){
        UserVo findOne = userFeginClient.findById(id);
        return findOne;
    }


    public UserVo findByIdfAllback(Long id){
        UserVo userVo = new UserVo();
        userVo.setId(-1L);
        userVo.setUsername("默认用户");
        return userVo;
    }

    @GetMapping("/log-user-instance")
    public void logUserInstance(){
        ServiceInstance instance = loadBalancerClient.choose(appId);
        log.info("{}:{}:{}",instance.getServiceId(),instance.getHost(),instance.getPort());
    }
}
