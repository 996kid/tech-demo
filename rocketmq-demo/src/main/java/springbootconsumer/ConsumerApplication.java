package springbootconsumer;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author 996kid
 * @Description ConsumerApplication
 * @Date 2021/1/9 16:12
 */
@SpringBootApplication
public class ConsumerApplication {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
