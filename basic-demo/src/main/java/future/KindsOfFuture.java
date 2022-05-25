package future;

import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author yyh
 * @Description KindsOfFuture
 * @Date 2020/8/21 15:58
 */
@Slf4j
public class KindsOfFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Map<String, Object> m = new HashMap<>();
        m.put("111", null);
        System.out.println(m);
//        test1();
    }


    public static void async() {
        DefaultEventLoop eventExecutors = new DefaultEventLoop();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventExecutors);

// 设置回调，异步接收结果
        promise.addListener(future -> {
            // 这里的 future 就是上面的 promise
            log.debug("{}",future.getNow());
        });

// 等待 1000 后设置成功结果
        eventExecutors.execute(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("set success, {}",10);
            promise.setSuccess(10);
        });

        log.debug("start...");
    }

    public static void sync() throws ExecutionException, InterruptedException {
        DefaultEventLoop eventExecutors = new DefaultEventLoop();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventExecutors);

        eventExecutors.execute(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("set success, {}" + 10);
            promise.setSuccess(10);
        });

        System.out.println("start...");
        System.out.println(promise.getNow()); // 还没有结果
        System.out.println(promise.get());
    }

    //等待前面步骤执行完
    public static void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runnable runnable1 = () -> {
            System.out.println("hello");
            countDownLatch.countDown();
        };

        Runnable runnable2 = () -> {
            System.out.println("world");
        };
        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        t1.start();
        countDownLatch.await();
//        t1.join();
        t2.start();
    }


    public static void test2() {

//任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 =
                CompletableFuture.runAsync(() -> {
                    System.out.println("T1:洗水壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T1:烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                });
//任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("T2:洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T2:洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);

                    System.out.println("T2:拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return "龙井";
                });
//任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 =
                f1.thenCombine(f2, (__, tf) -> {
                    System.out.println("T1:拿到茶叶:" + tf);
                    System.out.println("T1:泡茶...");
                    return "上茶:" + tf;
                });
//等待任务3执行结果
        System.out.println(f3.join());
    }

        static void sleep(int t, TimeUnit u) {
            try {
                u.sleep(t);
            }catch(InterruptedException e){}
        }
// 一次执行结果：
//        T1:洗水壶...
//        T2:洗茶壶...
//        T1:烧开水...
//        T2:洗茶杯...
//        T2:拿茶叶...
//        T1:拿到茶叶:龙井
//        T1:泡茶...
//        上茶:龙井



    /** 按顺序执行使用futuretask 实现
     * @throws InterruptedException
     */
    public static void test1() throws InterruptedException {
        FutureTask futureTask1 = new FutureTask(new Task1());
        FutureTask futureTask2 = new FutureTask(new Task2(futureTask1));

        Thread t1 = new Thread(futureTask1);
        Thread t2 = new Thread(futureTask2);
        t1.start();
        t2.start();
    }

    static class Task1 implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            System.out.println("hello");
            return "hello";
        }
    }

    static class Task2 implements Callable<String> {

        private FutureTask futureTask;

        Task2(FutureTask futureTask) {
            this.futureTask = futureTask;
        }

        @Override
        public String call() throws Exception {
            System.out.println();
            futureTask.get();
            System.out.println("world");
            return " world";
        }
    }
}
