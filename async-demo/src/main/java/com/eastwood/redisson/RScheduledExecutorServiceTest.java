package com.eastwood.redisson;

import org.redisson.api.RScheduledExecutorService;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 996kid@gmail.com
 * @Description RScheduledExecutorServiceTest
 * @Date 2021/5/7 10:41
 */
@Component
public class RScheduledExecutorServiceTest {

    @Resource
    private RedissonClient redissonClient;

    private RScheduledExecutorService rScheduledExecutorService;

//    @PostConstruct
//    public void init() {
//        rScheduledExecutorService = redissonClient.getExecutorService("rscheduledExecutor");
//    }

    public void publishTask(Runnable runnable) {
        rScheduledExecutorService = redissonClient.getExecutorService("rScheduledExecutor");
        rScheduledExecutorService.schedule(runnable, 10, TimeUnit.SECONDS);
    }
}
