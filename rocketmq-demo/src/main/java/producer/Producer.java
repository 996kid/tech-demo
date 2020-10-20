package producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/** 建议同一个 Group ID 只对应一种类型的 Topic，即一个TOPIC不同时用于顺序消息和无序消息的收发
 * @author yyh
 * @Description Producer
 * @Date 2020/10/14 16:14
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producerGroup1", true);
        producer.setNamesrvAddr("127.0.0.1:9876");
        //设置发送失败重试次数 默认为重试两次 可能导致消息重复
        producer.setRetryTimesWhenSendFailed(0);
//        producer.setRetryTimesWhenSendAsyncFailed(0);
        producer.start();
        for (int i = 0; i < 100; i++) {
            //同步发送消息, 使用keys分区
            Message message = new Message("topic1", "inOrder", "messageId_" + i,
                    ("hello world" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
//            SendResult sendResult = producer.send(message);
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    //根据i 分区
                    int index = (int) arg % mqs.size();
                    return mqs.get(index);
                }
            }, i);
            System.out.printf("SendResult: %s, queueId: %s %n", sendResult, sendResult.getMessageQueue().getQueueId());
        }
        System.out.println("all send...");
        producer.shutdown();
    }
    /**
     * 1. 消息类型： 普通消息，顺序消息，定时和延时消息，事务消息
     *    不同消息类型的topic不能混用
     */
}
