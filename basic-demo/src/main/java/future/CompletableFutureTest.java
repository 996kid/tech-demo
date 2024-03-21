package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author yyh
 * @Description completableFutureTest
 * @Date 2020/8/16 16:20
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws Exception {
        CompletableFuture completableFuture = new CompletableFuture();
        
        completableFuture.complete("true");

        completableFuture.get(10, TimeUnit.SECONDS);

        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureTest::fetchPrice);
        cf.thenApply(new Function<Double, Object>() {

            @Override
            public Object apply(Double aDouble) {
                return null;
            }
        });
        CompletableFuture cf2 = CompletableFuture.runAsync(() -> {
            System.out.println("等我干完");
        });
        CompletableFuture<Double> cf3 = cf2.thenCombine(cf, (__, price) -> price);
        // 如果执行成功:
        cf3.thenAccept((result) -> {
            System.out.println("cf3 result: " + result);
        });
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(500);

    }

    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
//        if (Math.random() < 0.3) {
//            throw new RuntimeException("fetch price failed!");
//        }
        return 5 + Math.random() * 20;
    }



    static void complateableFutureTest() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
//1、使用runAsync或supplyAsync发起异步调用
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            return "result1";
        }, executor);
//2、CompletableFuture.completedFuture()直接创建一个已完成状态的CompletableFuture
        CompletableFuture<String> cf2 = CompletableFuture.completedFuture("result2");
//3、先初始化一个未完成的CompletableFuture，然后通过complete()、completeExceptionally()，完成该CompletableFuture
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("success");

        // 没有返回结果
        CompletableFuture.runAsync(() -> {

        });



        CompletableFuture<String> cf3 = cf1.thenApply(result1 -> {
            //result1为CF1的结果
            //......
            return "result3";
        });
        CompletableFuture<String> cf5 = cf2.thenApply(result2 -> {
            //result2为CF2的结果
            //......
            return "result5";
        });


        CompletableFuture<String> cf4 = cf1.thenCombine(cf2, (result1, result2) -> {
            //result1和result2分别为cf1和cf2的结果
            return "result4";
        });


        CompletableFuture<Void> cf6 = CompletableFuture.allOf(cf3, cf4, cf5);
        CompletableFuture<String> result = cf6.thenApply(v -> {
            //这里的join并不会阻塞，因为传给thenApply的函数是在CF3、CF4、CF5全部完成时，才会执行 。
//            result3 = cf3.join();
//            result4 = cf4.join();
//            result5 = cf5.join();
            //根据result3、result4、result5组装最终result;
            return "result";
        });

        CompletableFuture<Object> cf7 = CompletableFuture.anyOf(cf3, cf4, cf5);
        cf7.thenApply(t -> {
            cf4.join();
            return cf3.join();
        });
    }

    /**
     * 每个CompletableFuture都可以被看作一个被观察者，其内部有一个Completion类型的链表成员变量stack，
     * 用来存储注册到其中的所有观察者。当被观察者执行完成后会弹栈stack属性，依次通知注册到其中的观察者。上面例子中步骤fn2就是作为观察者被封装在UniApply中。
     * 被观察者CF中的result属性，用来存储返回结果数据。这里可能是一次RPC调用的返回值，也可能是任意对象，在上面的例子中对应步骤fn1的执行结果。
     */
    static void complateableFutureTest2() {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "hello");

        CompletableFuture<String> cf2 = cf1.thenApply(s -> s + ", world");
    }


    /**
     * 该方法为美团内部rpc注册监听的封装，可以作为其他实现的参照
     * OctoThriftCallback 为thrift回调方法
     * ThriftAsyncCall 为自定义函数，用来表示一次thrift调用（定义如上）
     *
     * 回调 -> completableFuture
     */
//    public static <T> CompletableFuture<T> toCompletableFuture(final OctoThriftCallback<?,T> callback , ThriftAsyncCall thriftCall) {
//        //新建一个未完成的CompletableFuture
//        CompletableFuture<T> resultFuture = new CompletableFuture<>();
//        //监听回调的完成，并且与CompletableFuture同步状态
//        callback.addObserver(new OctoObserver<T>() {
//            @Override
//            public void onSuccess(T t) {
//                resultFuture.complete(t);
//            }
//            @Override
//            public void onFailure(Throwable throwable) {
//                resultFuture.completeExceptionally(throwable);
//            }
//        });
//        if (thriftCall != null) {
//            try {
//                thriftCall.invoke();
//            } catch (Exception e) {
//                resultFuture.completeExceptionally(e);
//            }
//        }
//        return resultFuture;
//    }
}
