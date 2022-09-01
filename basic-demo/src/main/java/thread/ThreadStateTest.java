package thread;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * @author 996kid@gmail.com
 * @Description ThreadStateTest
 * @Date 2022/5/11 10:30
 */
public class ThreadStateTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int corePoolSize = 4;
        int maximumPoolSize = 10;
        long keepAliveTime = 5000;
        new ConcurrentLinkedQueue();
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(10);
        ThreadFactory threadFactory = r -> {
            Thread thread = new Thread(r);
            thread.setUncaughtExceptionHandler((t, e) -> {
                System.out.println(e.getStackTrace());
            });
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

        // execute抛出未捕获的异常 当前线程会终止
//        executor.execute(() -> {
//            try {
//                System.out.println("My task is started running...");
//                System.out.println("Currend thread is " + Thread.currentThread().getId());
//                anotherMethod();
//            } catch (Throwable t) {
//                System.err.println("Uncaught exception is detected! " + t
//                        + " st: " + Arrays.toString(t.getStackTrace()));
//                throw t;
//            }
//        });

        // 真是没理解啊！！！
        Future future = executor.submit(() -> {
            try {
                System.out.println("My task is started running...");
                System.out.println("Currend thread is " + Thread.currentThread().getId());
                anotherMethod();
            } catch (Throwable t) {
                System.err.println("Uncaught exception is detected! " + t
                    + " st: " + Arrays.toString(t.getStackTrace()));
                throw t;
            }
        });
        future.get();
        Thread.sleep(1000000);
        executor.shutdown();
    }

    private static void anotherMethod() {
        throw new ArithmeticException();
    }
}
