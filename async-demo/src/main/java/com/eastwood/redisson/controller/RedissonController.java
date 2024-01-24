package com.eastwood.redisson.controller;

import com.eastwood.redisson.CustomTask;
import com.eastwood.redisson.RScheduledExecutorServiceTest;
import com.eastwood.redisson.delayqueue.DelayQueueService;
import com.eastwood.redisson.delayqueue.Task;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 996kid@gmail.com
 * @Description RedissonController
 * @Date 2021/5/7 11:15
 */
@RestController
@RequestMapping("/redisson")
@Slf4j
public class RedissonController {

    @Resource
    private RScheduledExecutorServiceTest scheduledExecutorServiceTest;

    @Resource
    private DelayQueueService delayQueueService;

    @Resource
    private RedissonClient redissonClient;


    @GetMapping("/test")
    public String test() {
        log.info("发布延迟任务");
        scheduledExecutorServiceTest.publishTask(new CustomTask());
//        delayQueueService.put(new Task("hello"));
//        Farther farther = new Farther();
//        Son son = new Son();
//        son.setName("oo");
//        farther.setName("kid");
//        farther.setSon(son);
//        redissonClient.getBucket("test").set(farther);

        RBloomFilter<Object> test = redissonClient.getBloomFilter("test");
        test.tryInit(1000000L, 0.03);
        return "success";
    }

    @GetMapping("/get")
    public Farther get() {
        return (Farther) redissonClient.getBucket("test").get();
    }
}
