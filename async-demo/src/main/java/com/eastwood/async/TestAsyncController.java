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
public class TestAsyncController extends BaseController{

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/async")
    public void async() {
        Future future = asyncService.asyncMethod();
        try {
            log.info((String)future.get());
        } catch (Exception e) {
            //swallow it
        }
    }

    @GetMapping("/test")
    public void testExtendsField() {
        asyncService.helloWorld();
    }

    @GetMapping("/")
    public String helloWorld() {
        return "hello-world";
    }
}
