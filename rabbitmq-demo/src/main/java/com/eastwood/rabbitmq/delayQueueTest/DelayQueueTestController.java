package com.eastwood.rabbitmq.delayQueueTest;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author 996kid
 * @desription
 * @date 2019/12/22
 */
@RestController
public class DelayQueueTestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //one, 使用死信队列模式
    @GetMapping("test")
    public  void test() throws IOException, TimeoutException {
        Connection connection = rabbitTemplate.getConnectionFactory().createConnection();
        Channel channel = connection.createChannel(false);
        //创建DLX及死信队列
        channel.exchangeDeclare("dlx.exchange", "direct");
        channel.queueDeclare("dlx.queue", true, false, false, null);
        channel.queueBind("dlx.queue", "dlx.exchange", "dlx.routingKey");
        //创建测试超时的Exchange及Queue
        channel.exchangeDeclare("delay.exchange", "direct");
        Map<String, Object> arguments = new HashMap<>();
        //过期时间10s
        arguments.put("x-message-ttl", 10000);
        //绑定DLX
        arguments.put("x-dead-letter-exchange", "dlx.exchange");
        //绑定发送到DLX的RoutingKey
        arguments.put("x-dead-letter-routing-key", "dlx.routingKey");
        channel.queueDeclare("delay.queue", true, false, false, arguments);
        channel.queueBind("delay.queue", "delay.exchange", "delay.routingKey");
        //发布一条消息
        channel.basicPublish("delay.exchange", "delay.routingKey", null, "该消息将在10s后发送到延迟队列".getBytes());
        channel.close();
        connection.close();
    }
}
