package com.eastwood.redisson.controller;

import com.eastwood.redisson.CustomTask;
import com.eastwood.redisson.RScheduledExecutorServiceTest;
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
public class RedissonController {

    @Resource
    private RScheduledExecutorServiceTest scheduledExecutorServiceTest;


    @GetMapping("/test")
    public String test() {
        scheduledExecutorServiceTest.publishTask(new CustomTask());
        return "Called...";
    }
}
