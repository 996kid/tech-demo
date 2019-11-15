package com.eastwood.rabbitmq;

import com.eastwood.rabbitmq.config.RabbitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author 996kid
 * @desription
 * @date 2019/11/12
 */
@SpringBootApplication
@EnableConfigurationProperties(RabbitConfig.class)
public class RabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }
}
