package com.eastwood.threadpool;

import java.util.concurrent.*;

/**
 * @author yyh
 * @Description JdkThreadPool
 * @Date 2020/8/18 14:11
 */
public class JdkThreadPool {

    public static void main(String[] args) {
        int corePoolSize = 10;
        int maximumPoolSize = 20;
        long keepAliveTime = 5000;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
        ThreadFactory threadFactory = r -> new Thread(r);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
        maximumPoolSize,
        keepAliveTime,
        TimeUnit.MILLISECONDS,
        workQueue,
        threadFactory, new ThreadPoolExecutor.AbortPolicy());
    }
}
