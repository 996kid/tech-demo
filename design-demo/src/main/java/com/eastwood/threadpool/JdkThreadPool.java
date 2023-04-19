package com.eastwood.threadpool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author yyh
 * @Description JdkThreadPool
 * @Date 2020/8/18 14:11
 */
public class JdkThreadPool {

    private static List<MyRunnable> cache = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int corePoolSize = 4;
        int maximumPoolSize = 10;
        long keepAliveTime = 5000;
        new ConcurrentLinkedQueue();
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(10);
        ThreadFactory threadFactory = r -> {
            Thread thread = new Thread(r);
            thread.setName("custom-" + thread.getId() );
            return thread;
        };
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
        maximumPoolSize,
        keepAliveTime,
        TimeUnit.MILLISECONDS,
        workQueue,
        threadFactory
        );
        //reject policy
        executor.setRejectedExecutionHandler((r, executor1) -> {
                    // save 到数据库/缓存
                    save(r);
                    // 延迟任务 拉取runnable 重新提交
                    MyRunnable myRunnable = scheduleGetMyRunnable();
                    executor.submit(myRunnable);
                }
        );
//        Future<String> future = executor.submit(new MyCallable());
//        System.out.println(future.get());
//        System.out.println(executor.getCorePoolSize());
        for (int i = 0; i < 100; i++) {
            Future future = executor.submit(new MyRunnable(0, "L3vi-" + i));
        }
        Thread.sleep(50009);
        executor.shutdown();
    }

    private static MyRunnable scheduleGetMyRunnable() {
        return cache.remove(0);
    }

    /** 保存入库
     * @param r
     */
    private static void save(Runnable r) {
        cache.add((MyRunnable) r);
    }
}

class MyCallable implements Callable<String> {

    @Override
    public String call() {
        int i = 1/0;
        return "my callable";
    }
}

class MyRunnable implements Runnable, Serializable {

    // 状态 未运行 已运行
    private int status;

    private String name;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    MyRunnable(int status, String name) {
        this.status = status;
        this.name = name;
    }

    @Override
    public void run() {
//        try {
            System.out.println("my name is " + name);
//            int i = 1/0;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }
}
