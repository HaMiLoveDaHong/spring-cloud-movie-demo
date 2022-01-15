package com.zhw.cloud.microservicegatewayzuulaggregation.controller;

import com.google.common.collect.Maps;
import com.zhw.cloud.microservicegatewayzuulaggregation.po.User;
import com.zhw.cloud.microservicegatewayzuulaggregation.service.AggregationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;

import java.util.HashMap;

/**
 * 聚合服务
 * @Author zhw
 * @since 2022/1/12
 */
@Slf4j
@RestController
@RequestMapping("/aggregation")
public class AggregationController {

    @Autowired
    AggregationService aggregationService;

    @GetMapping("/user/{id}")
    public DeferredResult<HashMap<String, User>> userById(@PathVariable Long id){
        aggregationService.getMoveieUserById(id);
        Observable<HashMap<String, User>> result = this.aggregationObservable(id);
        return this.toDeferredResult(result);
    }

    private Observable<HashMap<String, User>> aggregationObservable(Long id) {
        //合并多个观察者
        return Observable.zip(
                this.aggregationService.getUserById(id),
                this.aggregationService.getMoveieUserById(id),
                (user,movieUser) -> {
                    HashMap<String,User> map = new HashMap<>();
                    map.put("user",user);
                    map.put("movieUser",movieUser);
                    return map;
                }
        );
    }


    public DeferredResult<HashMap<String, User>> toDeferredResult(Observable<HashMap<String, User>> details){
        DeferredResult<HashMap<String, User>> result = new DeferredResult<>();

        details.subscribe(new Observer<HashMap<String, User>>() {
            @Override
            public void onCompleted() {
                log.info("完成");
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("发生错误...",throwable);
            }

            @Override
            public void onNext(HashMap<String, User> stringUserHashMap) {
                result.setResult(stringUserHashMap);
            }
        });
        return result;
    }
}
