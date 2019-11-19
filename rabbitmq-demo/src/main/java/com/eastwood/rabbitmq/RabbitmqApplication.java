package com.eastwood.rabbitmq;

import com.eastwood.rabbitmq.config.RabbitConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author 996kid
 * @desription Rabbitmq
 * @date 2019/11/12
 */
@SpringBootApplication
@EnableConfigurationProperties(RabbitConfig.class)
public class RabbitmqApplication {

    //一、mq ack机制保证数据安全
    //二、Delivery Tags 在channel域内唯一标识本次delivery
    //三、手动ack的几种方式：
    // 乐观ack：1，basic.ack 简单的通知rabbitmq将消息记录为已被推送且能被删除
    // 悲观ack: 2, basic.reject 同样能完成上面的功能，不同的是乐观ack假设消息已经被
    //          处理，而悲观ack认为消息虽然未被处理但是仍然需要删除

    //四、自动ack
    //  提供了更高的吞吐量，但是牺牲了传递和消费者端处理消息的安全性。
    //  在这种模式下，在成功传递信息之前，如果消费者的tcp连接或者channel被关闭了
    //  那么消息会丢失。所以它是不安全的
    //  另外自动ack存在 消费者过载问题，因此只适用于能以一个稳定的频率高效的处理消息的消费者
    // 1.negatively acknowledge, the message will
    //        be discarded
    //        channel.basicReject(deliveryTag, false);
    // 2.requeue the delivery
    // channel.basicReject(deliveryTag, true);

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

    static final String topicExchangeName = "spring-boot-exchange";

    static final String queueName = "spring-boot";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
