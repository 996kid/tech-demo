package springbootconsumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author 996kid
 * @Description Consumer
 * @Date 2021/1/9 16:18
 */
@Service
@RocketMQMessageListener(topic = "topic1", consumerGroup = "group1", selectorExpression = "springbootmessage1")
public class Consumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String o) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
