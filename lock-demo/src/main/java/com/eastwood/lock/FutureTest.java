package com.eastwood.lock;

import java.util.concurrent.*;

/**
 * @author yyh
 * @Description FutureTest
 * @Date 2020/9/14 21:58
 */
public class FutureTest {

    public static void main(String[] args) {
        //无界队列
//        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                4,
                10000,
                TimeUnit.MILLISECONDS,
                blockingQueue,
                r -> {
                    Thread t = new Thread();
                    t.setName("custome thread");
                    return t;
                },
                new ThreadPoolExecutor.AbortPolicy());
    }
}

/**
 * 自定义策略
 */
class CustomAbortPolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        throw new RejectedExecutionException("Task " + r.toString() +
                " rejected from " +
                executor.toString());
    }
}
