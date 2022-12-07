package com.eastwood;

import com.eastwood.redisson.delayqueue.DelayQueueService;
import com.eastwood.redisson.delayqueue.Task;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;

/**
 * @author 996kid
 * @desription <p>测试spring boot 相关的内容诶</p>
 * @date 2019/10/30
 */
@SpringBootApplication
@EnableAsync
public class AsyncApplication implements ApplicationRunner {

    @Resource
    private DelayQueueService delayQueueService;

    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            System.out.println("消费阻塞队列ing");
            Task task = delayQueueService.take();
            System.out.println(task.getItem());
        }
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
