package springbootproducer;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author 996kid
 * @Description ProducerApplication
 * @Date 2021/1/9 15:09
 */
@SpringBootApplication
public class ProducerApplication implements ApplicationRunner {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        rocketMQTemplate.syncSend("topic1:springbootmessage", "hello!");
    }
}
