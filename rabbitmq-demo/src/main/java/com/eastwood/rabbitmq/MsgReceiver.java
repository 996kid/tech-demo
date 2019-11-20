package com.eastwood.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;


/**
 * @author 996kid
 * @desription
 * @date 2019/11/18
 */
@Slf4j
@Component
public class MsgReceiver {

    private final RedisTemplate<String, String> redisTemplate;

    public MsgReceiver(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    @RabbitListener(queues = "QUEUE_A")
    @RabbitHandler
    public void messageHanlder(Message message, Channel channel) throws IOException {
//        int i=10/0;
        log.info("message received [" + new String(message.getBody()) + "]");
        String messageId = message.getMessageProperties().getMessageId();
        if (!StringUtils.isEmpty(messageId)) {
            try{
                //利用redis 单线程和setnx命令 保证消息幂等性
                if(redisTemplate.opsForValue().setIfAbsent(messageId, "1")) {
                    //business code, process message
                    log.info("process message .....");
                } else {
                    //do nothing
                    log.info("i do nothing");
                }
                int i=10/0;
                //手动ack
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            }catch (Exception e) {
                redisTemplate.delete(messageId);
                log.error(e.getMessage());
            }
        } else {
            log.error("消息中不包含messageId，已过滤");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        }


        // 1.negatively acknowledge, the message will
        //        be discarded
//        channel.basicReject(deliveryTag, false);
        // 2.requeue the delivery
//        channel.basicReject(deliveryTag, true);

        // requeue all unacknowledged deliveries up to
        // this delivery tag
//        channel.basicNack(deliveryTag, true, true);

        // requeue all unacknowledged deliveries up to
        // this delivery tag
//        channel.BasicNack(ea.DeliveryTag, true, true);
    }
}
