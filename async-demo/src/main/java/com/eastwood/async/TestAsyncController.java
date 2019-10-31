package com.eastwood.async;

import com.eastwood.async.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * @author 996kid
 * @desription
 * @date 2019/10/30
 */
@RestController
@Slf4j
public class TestAsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/async")
    public void async() {
        long start = System.currentTimeMillis();
        log.info("begin at {}", start);
        Future future = asyncService.asyncMethod();
        long back = System.currentTimeMillis();
        log.info("back cost {}ms", back - start);
        try {
            log.info((String)future.get());
        } catch (Exception e) {
            //swallow it
        }
        long end = System.currentTimeMillis();
        log.info("总耗时： {}ms", end - start);
    }
}
