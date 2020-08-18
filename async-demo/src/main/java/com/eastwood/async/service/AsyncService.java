package com.eastwood.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author 996kid
 * @desription
 * @date 2019/10/30
 */
@Service
@Slf4j
public class AsyncService {

    @Async("asyncThreadPoolExecutor")
    public Future<String> asyncMethod() {
        try {
            log.info("asyncMethod called, current thread is " + Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult("result from async thread");
    }

    @Async("asyncThreadPoolTaskExecutor")
    public void helloWorld() {
        System.out.println("hello-world");
    }
}
