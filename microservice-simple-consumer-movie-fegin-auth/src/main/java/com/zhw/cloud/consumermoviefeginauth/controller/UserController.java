package com.zhw.cloud.consumermoviefeginauth.controller;

import com.zhw.cloud.consumermoviefeginauth.config.FeignConfiguration;
import com.zhw.cloud.consumermoviefeginauth.feign.UserFeginClient;
import com.zhw.cloud.consumermoviefeginauth.vo.UserVo;
import feign.*;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static com.zhw.cloud.consumermoviefeginauth.common.StaticCommon.Url;

/**
 * @Author zhw
 * @since 2021/11/1
 */
@Import(FeignClientsConfiguration.class)
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
//    @Autowired
    private UserFeginClient userFeginClient;
//
    private UserFeginClient userFeginClientByAdmin;

    public UserController(Decoder decoder, Encoder encoder, Client client, Contract contract){
        this.userFeginClientByAdmin= Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin","123456")).logLevel(Logger.Level.FULL)
                .target(UserFeginClient.class,"http://MICROSERVICE-SIMPLE-PROVIDER-USER-AUTH/");
        List<RequestInterceptor> requestInterceptors = new ArrayList<>();
        requestInterceptors.add(new FeignConfiguration.FeignBasicAuthRequestInterceptor());
        requestInterceptors.add(new BasicAuthRequestInterceptor("user","123456"));
        userFeginClient= Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptors(requestInterceptors).logLevel(Logger.Level.FULL)
                .target(UserFeginClient.class,"http://MICROSERVICE-SIMPLE-PROVIDER-USER-AUTH/");
    }

    @GetMapping("/{id}")
    public UserVo findById(@PathVariable Long id, HttpServletRequest request){
        UserVo findOne = userFeginClient.findById(id);
        return findOne;
    }

    @GetMapping("/get")
    public String get(@RequestParam Map<String,Object> map){
        String findOne = userFeginClient.get(map);
        return findOne;
    }

    @GetMapping("/post")
    public String get(){
        UserVo userVo = new UserVo();
        userVo.setId(1);
        userVo.setUsername("lisi");
        String findOne = userFeginClient.post(userVo);
        return findOne;
    }

    @GetMapping("/admin/{id}")
    public UserVo roleFindById(@PathVariable Long id, HttpServletRequest request){
        UserVo findOne = userFeginClientByAdmin.findById(id);
        return findOne;
    }

    @GetMapping("/auth/{id}")
    public UserVo authFindById(@PathVariable Long id){
        UserVo findOne = userFeginClient.findById(id);
        return findOne;
    }

    @GetMapping("/log-user-instance")
    public void logUserInstance(){
        ServiceInstance instance = loadBalancerClient.choose(appId);
        log.info("{}:{}:{}",instance.getServiceId(),instance.getHost(),instance.getPort());
    }
}
