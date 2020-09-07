package com.eastwood.springevent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author yyh
 * @Description SmsService
 * @Date 2020/9/7 16:18
 */
@Service
public class SmsService implements ApplicationListener<NewOrderEvent> {

    @Override
    public void onApplicationEvent(NewOrderEvent event) {
        System.out.println("发送短信了。。");
    }
}
