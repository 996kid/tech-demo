package consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author yyh
 * @Description ScheduleConsumer
 * @Date 2020/10/15 16:06
 */
public class ScheduleConsumer {

    public static void main(String[] args) throws MQClientException {
        // 实例化消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerGroup3");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        // 订阅Topics
        consumer.subscribe("topic1", "schedule");
        // 注册消息监听者
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext context) {
                // 延迟消息 消息生成 和 消息store 之间延迟
                // 在broker中延迟保存
                for (MessageExt message : messages) {
                    // Print approximate delay time period
                    System.out.println("Receive message[msgId=" + message.getMsgId() + "] " + (message.getStoreTimestamp() - message.getBornTimestamp()) + "ms later");
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动消费者
        consumer.start();
    }

    /**
     * 1. 完善外网demo
     * 2. consumeQueue 构建过程
     * 3. spring boot 整合
     * 4. 事务消息
     */
}
