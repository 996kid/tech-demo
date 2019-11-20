package com.eastwood.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

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

    /**
     * 使用redis setnx 保证消息传递的幂等性
     * 每个message都带上全局唯一的id
     * @param content
     */
    public void sendMsg2(String content) {
        Message message = MessageBuilder
                .withBody(content.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8")
                .setMessageId(UUID.randomUUID() + "")
                .build();
        rabbitTemplate.convertAndSend(EXCHANGE_A, ROUTING_KEY_A, message);
    }

}