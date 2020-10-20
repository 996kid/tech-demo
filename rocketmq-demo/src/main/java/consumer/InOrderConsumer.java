package consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Date;
import java.util.List;

/** 不同的consumer group  对应不同的offset
 * @author yyh
 * @Description InOrderConsumer
 * @Date 2020/10/14 16:33
 */
public class InOrderConsumer {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerGroup1", true);
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("topic1", "inOrder");
        //一个队列对应一个消费者线程
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                context.setAutoCommit(true);
                msgs.stream().forEach(m -> {
                    System.out.printf("consume thread: %s, queueId: %s, message content: %s %n",
                            Thread.currentThread().getName(),
                            m.getQueueId(),
                            new String(m.getBody()));
                });
                System.out.println("处理业务逻辑中。。。");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                msgs.stream().forEach(m -> {
//                    System.out.printf("%s consume thread: %s, queueId: %s, message content: %s %n", System.currentTimeMillis(),
//                            Thread.currentThread().getName(),
//                            m.getQueueId(),
//                            new String(m.getBody()));
//                });
//                System.out.println("处理业务逻辑中。。。");
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
        consumer.start();
    }
}
