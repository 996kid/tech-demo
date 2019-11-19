package com.eastwood.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author 996kid
 * @desription
 * @date 2019/11/18
 */
@Slf4j
@Component
public class MsgReceiver {

    @RabbitListener(queues = "QUEUE_A")
    @RabbitHandler
    public void messageHanlder(Message message, Channel channel) throws IOException {
//        int i=10/0;
        log.info("message received [" + new String(message.getBody()) + "]");
        //手动ack
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);


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
