package com.zhw.cloud.consumermoviefegincustomzing.controller;

import com.zhw.cloud.consumermoviefegincustomzing.feign.UserFeginClient;
import com.zhw.cloud.consumermoviefegincustomzing.vo.UserVo;
import feign.Client;
import feign.Contract;
import feign.Feign;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import static com.zhw.cloud.consumermoviefegincustomzing.common.StaticCommon.Url;

/**
 * @Author zhw
 * @since 2021/11/1
 */
@Slf4j
@RestController
@RequestMapping(Url+"user")
@Import(FeignClientsConfiguration.class)
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

//    public UserController(Decoder decoder, Encoder encoder, Client client, Contract contract){
//        this.userFeginClient= Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
//                .requestInterceptor(new BasicAuthRequestInterceptor("user","123456"))
//                .target(UserFeginClient.class,"http://MICROSERVICE-SIMPLE-PROVIDER-USER/");
//    }

    @GetMapping("/{id}")
    public UserVo findById(@PathVariable Long id, HttpServletRequest request){
        UserVo findOne = userFeginClient.findById(id);
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
