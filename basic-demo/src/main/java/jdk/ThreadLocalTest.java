package jdk;

import java.util.concurrent.*;

/**
 * @author yyh
 * @Date 2020/8/12 16:18
 */
public class ThreadLocalTest implements Runnable {

    private int counter;

    private ThreadLocal<Byte[]> threadLocal = new ThreadLocal<>();

    private static final int _10M = 1024 * 1024 * 10;

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(256), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("custompool-" + thread.getId());
            return thread;
        }
    });

    public ThreadLocalTest(int i) {
        counter = i;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.submit(new ThreadLocalTest(i));
        }
        Thread.sleep(Integer.MAX_VALUE);
        threadPoolExecutor.shutdown();
    }

    @Override
    public void run() {
        threadLocal.set(new Byte[_10M]);
        System.out.println(counter);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
