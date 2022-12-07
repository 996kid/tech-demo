package com.eastwood.redisson;

import org.redisson.Redisson;
import org.redisson.api.RScheduledExecutorService;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Callable;
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
        rScheduledExecutorService = redissonClient.getExecutorService("se");
        rScheduledExecutorService.schedule(runnable, 5, TimeUnit.SECONDS);
    }
}
