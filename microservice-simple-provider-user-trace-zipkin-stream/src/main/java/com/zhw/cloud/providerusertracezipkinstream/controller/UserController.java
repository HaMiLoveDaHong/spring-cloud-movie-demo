package com.zhw.cloud.providerusertracezipkinstream.controller;

import com.zhw.cloud.providerusertracezipkinstream.dao.UserReository;
import com.zhw.cloud.providerusertracezipkinstream.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.zhw.cloud.providerusertracezipkinstream.common.StaticCommon.Url;

/**
 * @Author zhw
 * @since 2021/10/31
 */
@RestController
@RequestMapping(Url)
public class UserController {

    @Autowired
    private UserReository userReository;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        User findOne = userReository.findOne(id);
        return findOne;
    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo(){
        return discoveryClient.getInstances("microservice-simple-provider-user");
    }
}
