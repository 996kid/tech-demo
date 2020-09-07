package com.eastwood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author 996kid
 * @desription 测试spring boot 异步调用,处理异步调用返回值
 * @date 2019/10/30
 */
@SpringBootApplication
@EnableAsync
public class AsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
    }

    @Configuration
    public class AsyncEventConfig {

        @Bean(name = "applicationEventMulticaster")
        public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
            SimpleApplicationEventMulticaster eventMulticaster
                    = new SimpleApplicationEventMulticaster();

            eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
            return eventMulticaster;
        }

    }
}
