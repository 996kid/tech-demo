package com.eastwood.springevent;

import org.springframework.context.ApplicationEvent;

/**
 * 测试spring 的事件监听机制
 * @author yyh
 * @Description NewOrderEvent
 * @Date 2020/9/7 16:16
 */
public class NewOrderEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public NewOrderEvent(Object source) {
        super(source);
    }
}
