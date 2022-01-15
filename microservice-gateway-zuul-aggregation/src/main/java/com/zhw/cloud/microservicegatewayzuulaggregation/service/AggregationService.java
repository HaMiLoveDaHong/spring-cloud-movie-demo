package com.zhw.cloud.microservicegatewayzuulaggregation.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhw.cloud.microservicegatewayzuulaggregation.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;


/**
 * @Author zhw
 * @since 2022/1/12
 */
@Service
public class AggregationService {

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getUserById(Long id){
        //创建一个被观察者
        return Observable.create(observer->{
            User user = restTemplate.getForObject("http://microservice-simple-provider-user/user/{id}",User.class,id);
            observer.onNext(user);
            observer.onCompleted();
        });
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getMoveieUserById(Long id){
        //创建一个被观察者
        return Observable.create(observer->{
            User user = restTemplate.getForObject("http://microservice-simple-consumer-movie-hystrix-turbine-mq/movie/user/{id}",User.class,id);
            observer.onNext(user);
            observer.onCompleted();
        });
    }


    public User fallback(Long id){
        User user = new User();
        user.setId(-1L);
        return user;
    }
}
