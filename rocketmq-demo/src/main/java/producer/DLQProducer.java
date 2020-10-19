package producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author yyh
 * @Description DLQProducer
 * @Date 2020/10/16 15:22
 */
public class DLQProducer {

    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("producerGroup5");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        producer.send(new Message("topic1", "dlq",
                "DLQ test...".getBytes(RemotingHelper.DEFAULT_CHARSET)));
        producer.shutdown();
    }
}
