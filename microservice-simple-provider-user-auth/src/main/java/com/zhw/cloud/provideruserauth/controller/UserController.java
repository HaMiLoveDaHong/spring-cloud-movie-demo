package com.zhw.cloud.provideruserauth.controller;

import com.alibaba.fastjson.JSON;
import com.zhw.cloud.provideruserauth.dao.UserReository;
import com.zhw.cloud.provideruserauth.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.zhw.cloud.provideruserauth.common.StaticCommon.Url;

/**
 * @Author zhw
 * @since 2021/10/31
 */
@Slf4j
@RestController
@RequestMapping(Url)
public class UserController {

    @Autowired
    private UserReository userReository;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            UserDetails user = (UserDetails) principal;
            Collection<? extends GrantedAuthority> collection = user.getAuthorities();
            for (GrantedAuthority c:collection){
                //打印当前登录用户的信息
                log.info("当前用户是{}，角色是{}",user.getUsername(),c.getAuthority());
            }
        }else {

        }
        User findOne = userReository.findOne(id);
        return findOne;
    }

    @GetMapping(value = "/get")
    public String get(@RequestParam Map<String,Object> map){
        String result = JSON.toJSONString(map);
        log.info("参数：{}", result);
        return result;
    }

    @PostMapping(value = "/post")
    public String post(@RequestBody User user){
        String result = JSON.toJSONString(user);
        log.info("参数：{}", result);
        return result;
    }





    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo(){
        return discoveryClient.getInstances("microservice-simple-provider-user");
    }
}
