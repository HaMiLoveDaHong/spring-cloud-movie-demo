package com.zhw.cloud.consumermoviehystrixfeignfallfactory.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhw.cloud.consumermoviehystrixfeignfallfactory.vo.UserVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 优先级:
 * fallback，fallbackFactory，fallbackMethod 设置，fallback执行，其他则不执行
 * fallbackFactory，fallbackMethod 设置，fallbackFactory执行，其他则不执行
 * fallbackMethod 设置，fallbackMethod执行
 * @Author zhw
 * @since 2021/11/5
 */
@FeignClient(name = "MICROSERVICE-SIMPLE-PROVIDER-USER",fallbackFactory = UserFeginClient.UserFeginClientFallbackFactory.class)
public interface UserFeginClient {

    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public UserVo findById(@PathVariable("id") Long id);


    /**
     * 回退类UserFeginClientImpl需实现 feign client 接口
     * UserFeginClientImpl 也可以是 public class ,没有区别
     */
    @Component
    class UserFeginClientImpl implements UserFeginClient{

        @Override
        public UserVo findById(Long id) {
            UserVo userVo = new UserVo();
            userVo.setId(-2L);
            userVo.setUsername("默认用户2");
            return userVo;
        }
    }

    @Component
    @Slf4j
    class UserFeginClientFallbackFactory implements FallbackFactory<UserFeginClient>{

        @Override
        public UserFeginClient create(Throwable throwable) {
            return new UserFeginClient() {
                @Override
                public UserVo findById(Long id) {
                    //日志最好放在各个fallback方法中，而不是直接在create方法中
                    //否则在引用启动时，就会打印该日志
                    log.error("fallback的原因是:{}",throwable);
                    UserVo userVo = new UserVo();
                    userVo.setId(-3L);
                    userVo.setUsername("默认用户3");
                    return userVo;
                }
            };
        }
    }
}
