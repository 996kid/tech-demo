package com.eastwood.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MsgProducer {

    private static final String EXCHANGE_A = "my-mq-exchange_A";

    private static final String ROUTING_KEY_A = "spring-boot-routingKey_A";
 
    private RabbitTemplate rabbitTemplate;
    /**
     * 构造方法注入rabbitTemplate
     */
    @Autowired
    public MsgProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
 
    public void sendMsg(String content) {
        rabbitTemplate.convertAndSend(EXCHANGE_A, ROUTING_KEY_A, content);
    }

}