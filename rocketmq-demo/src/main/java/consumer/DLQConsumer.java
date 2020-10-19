package consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author yyh
 * @Description DLQConsumer
 * @Date 2020/10/16 15:38
 */
public class DLQConsumer {

    public static void main(String[] args) throws  Exception {
        // 实例化消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerGroup5");
        // 设置NameServer的地址
        consumer.setNamesrvAddr("localhost:9876");
        // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
        consumer.subscribe("topic1", "dlq");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                // 返回此状态会判断是否存在重试topic 没有就创建
                //一直重试
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        System.out.println("all consumed...");
        consumer.start();
    }
}
