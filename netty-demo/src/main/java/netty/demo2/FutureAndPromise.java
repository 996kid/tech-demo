package netty.demo2;

import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @author 996kid@gmail.com
 * @Description FutureAndPromise
 * @Date 2022/5/17 0:05
 */
@Slf4j
public class FutureAndPromise {

    public static void main(String[] args) {
        test1();
        
//        test2();

//        test3();

//        test4();

//        test5();

//        test6();
    }

    /**
     *  死锁检查
     */
    private static void test6() {
        DefaultEventLoop eventExecutors = new DefaultEventLoop();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventExecutors);

        eventExecutors.submit(()->{
            System.out.println("1");
            try {
                promise.await();
                // 注意不能仅捕获 InterruptedException 异常
                // 否则 死锁检查抛出的 BlockingOperationException 会继续向上传播
                // 而提交的任务会被包装为 PromiseTask，它的 run 方法中会 catch 所有异常然后设置为 Promise 的失败结果而不会抛出
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("2");
        });
        eventExecutors.submit(()->{
            System.out.println("3");
            try {
                promise.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("4");
        });
    }

    private static void test5() {
        DefaultEventLoop eventExecutors = new DefaultEventLoop();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventExecutors);

        promise.addListener(future -> {
            log.debug("result {}", (promise.isSuccess() ? promise.getNow() : promise.cause()).toString());
        });

        eventExecutors.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RuntimeException e = new RuntimeException("error...");
            log.debug("set failure, {}", e.toString());
            promise.setFailure(e);
        });

        log.debug("start...");
        eventExecutors.shutdownGracefully();
    }

    /**
     * 同步处理任务失败
     */
    private static void test4() {
        DefaultEventLoop eventExecutors = new DefaultEventLoop();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventExecutors);

        eventExecutors.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RuntimeException e = new RuntimeException("error...");
            log.debug("set failure, {}", e.toString());
            promise.setFailure(e);
        });

        log.debug("start...");
        log.debug("{}", promise.getNow());
        try {
            promise.await(); // 与 sync 和 get 区别在于，不会抛异常
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("result {}", (promise.isSuccess() ? promise.getNow() : promise.cause()).toString());
        eventExecutors.shutdownGracefully();
    }

    /**
     * 同步处理任务失败 - sync & get
     */
    private static void test3() {
        DefaultEventLoop eventExecutors = new DefaultEventLoop();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventExecutors);

        eventExecutors.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RuntimeException e = new RuntimeException("error...");
            log.debug("set failure, {}", e.toString());
            promise.setFailure(e);
        });

        log.debug("start...");
        log.debug("{}", promise.getNow());
        try {
            promise.get(); // sync() 也会出现异常，只是 get 会再用 ExecutionException 包一层异常
            promise.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        eventExecutors.shutdownGracefully();
    }

    /**
     * 异步处理任务成功
     */
    private static void test2() {
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
            promise.setSuccess(10);
            log.debug("set success, {}",10);
        });

        log.debug("start...");
        eventExecutors.shutdownGracefully();
    }

    /**
     * 同步处理任务成功
     */
    private static void test1() {
        DefaultEventLoop eventExecutors = new DefaultEventLoop();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventExecutors);
        eventExecutors.execute(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            promise.setSuccess(10);
            log.debug("set success, {}",10);
        });

        log.debug("start...");
        log.debug("{}",promise.getNow()); // 还没有结果
        try {
            log.debug("{}", promise.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        eventExecutors.shutdownGracefully();
    }


//    public static void ss() {
//        DefaultPromise defaultPromise = new DefaultPromise();
//
//    }
}
