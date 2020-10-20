package consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @author yyh
 * @Description AsyncConsumer
 * @Date 2020/10/15 11:16
 */
public class AsyncConsumer {

    public static void main(String[] args) throws MQClientException {
        // 实例化消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerGroup2");

        // 设置NameServer的地址
        consumer.setNamesrvAddr("localhost:9876");
        //默认集群消费
//        consumer.setMessageModel(MessageModel.BROADCASTING);
        // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
        consumer.subscribe("topic1", "async");
        // 注册回调实现类来处理从broker拉取回来的消息
        // 一个队列有多个消费者线程并发消费，不会等到上一条消息处理完逻辑
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 标记该消息已经被成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动消费者实例
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
