package com.eastwood.threadpool;

import java.util.concurrent.*;

/**
 * @author yyh
 * @Description JdkThreadPool
 * @Date 2020/8/18 14:11
 */
public class JdkThreadPool {

    public static void main(String[] args) {
        int corePoolSize = 4;
        int maximumPoolSize = 10;
        long keepAliveTime = 5000;
        new ConcurrentLinkedQueue();
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>();
        ThreadFactory threadFactory = r -> new Thread(r);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
        maximumPoolSize,
        keepAliveTime,
        TimeUnit.MILLISECONDS,
        workQueue,
        threadFactory, new ThreadPoolExecutor.AbortPolicy());
        //reject policy

        Future<String> future = executor.submit(new MyCallable());
        while (future.isDone()) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "my callable";
    }
}
