package com.eastwood.rabbitmq.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author 996kid
 * @desription
 * @date 2019/11/12
 */
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Slf4j
@Data
public class RabbitConfig {

    private String host;

    private int port;

    //集群配置
//    private String address;

    private String username;

    private String password;

    private String virtualHost;


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setAddresses(address);
        connectionFactory.setHost(host);
        log.info(host +", " + port + ", " + username + ", " + password + ", " + virtualHost);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

}
