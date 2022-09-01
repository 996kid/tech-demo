package future;

import java.util.concurrent.CompletableFuture;

/**
 * @author yyh
 * @Description completableFutureTest
 * @Date 2020/8/16 16:20
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureTest::fetchPrice);
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
}
