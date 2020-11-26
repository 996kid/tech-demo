package com.eastwood.springevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author yyh
 * @Description OrderService
 * @Date 2020/9/7 15:57
 */
@Service
public class OrderService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 下单除了发短信外 后续可能增加或减少 其他通知业务
     *  为了避免后续需要手动增删代码
     *  利用spring事件监听机制 以增量方式应对需求变更
     * 默认是同步的
     */
    public void createOrder() {
        System.out.println("下单成功");
        //both works
        applicationEventPublisher.publishEvent(new NewOrderEvent(this));
        applicationContext.publishEvent(new NewOrderEvent(this));

        System.out.println("create order finished...");
        //发送短信
        //sendMsg();
        //通知仓库发货
        //notifySend();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
