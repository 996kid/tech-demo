package com.eastwood.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author 996kid
 * @desription 消息发送到broker触发回调
 * @date 2019/11/18
 */
@Component
@Slf4j
public class PublisherConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    private final RabbitTemplate rabbitTemplate;

    public PublisherConfirmCallBack(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        log.info("消息唯一标识： " + correlationData.getId());
        log.info("确认结果： " + ack);
        log.info("失败原因： " + cause);
    }
}
