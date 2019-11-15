package com.eastwood.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 996kid
 * @desription
 * @date 2019/11/12
 */
@RestController
public class TestController {

    @Autowired
    private MsgProducer msgProducer;

    @GetMapping("/test")
    public void test() {
        msgProducer.sendMsg("hihihihihihihihih");
    }
}
