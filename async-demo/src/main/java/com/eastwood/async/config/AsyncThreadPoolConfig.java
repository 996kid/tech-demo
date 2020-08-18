package com.eastwood.async.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Optional;
import java.util.concurrent.*;

/**
 * @author 996kid
 * @desription
 * @date 2019/10/30
 */
@Configuration
public class AsyncThreadPoolConfig {

    @Bean
    public ThreadPoolExecutor asyncThreadPoolExecutor(){
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(20);
//        executor.setMaxPoolSize(40);
//        executor.setQueueCapacity(25);
//        executor.setKeepAliveSeconds(60);
//        executor.setThreadNamePrefix("asyncThread");
//        executor.setWaitForTasksToCompleteOnShutdown(true);
//        executor.setAwaitTerminationSeconds(60);
//
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//
//        executor.initialize();
//        return executor;
        //使用concurrent包中的ThreadPoolExecutor
        //这里指定线程核心池为10 初始化时并不会立即生成10个线程 而是在真正执行任务的时候才生成
        int corePoolSize = 10;
        int maximumPoolSize = 20;
        long keepAliveTime = 5000;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(10);
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setThreadFactory(r -> {
            Thread thread = new Thread(r);
            return thread;
        }).setNameFormat("asyn-pool-%d").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                workQueue,
                threadFactory, new ThreadPoolExecutor.AbortPolicy());
        return executor;

        /**
         * corePoolSize：线程池核心线程的数量，默认值为1（这就是默认情况下的异步线程池配置使得线程不能被重用的原因）。
         *
         * maxPoolSize：线程池维护的线程的最大数量，只有当核心线程都被用完并且缓冲队列满后，才会开始申超过请核心线程数的线程，默认值为Integer.MAX_VALUE。
         *
         * queueCapacity：缓冲队列。
         *
         * keepAliveSeconds：超出核心线程数外的线程在空闲时候的最大存活时间，默认为60秒。
         *
         * threadNamePrefix：线程名前缀。
         *
         * waitForTasksToCompleteOnShutdown：是否等待所有线程执行完毕才关闭线程池，默认值为false。
         *
         * awaitTerminationSeconds：waitForTasksToCompleteOnShutdown的等待的时长，默认值为0，即不等待。
         *
         * rejectedExecutionHandler：当没有线程可以被使用时的处理策略（拒绝任务），默认策略为abortPolicy，包含下面四种策略：
         *
         * callerRunsPolicy：用于被拒绝任务的处理程序，它直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务。
         *
         * abortPolicy：直接抛出java.util.concurrent.RejectedExecutionException异常。
         *
         * discardOldestPolicy：当线程池中的数量等于最大线程数时、抛弃线程池中最后一个要执行的任务，并执行新传入的任务。
         *
         * discardPolicy：当线程池中的数量等于最大线程数时，不做任何动作。
         */
    }
}
