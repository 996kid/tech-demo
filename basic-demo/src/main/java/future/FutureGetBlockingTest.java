package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 996kid@gmail.com
 * @Description FutureGetBlockingTest
 * @Date 2022/8/6 22:49
 */
public class FutureGetBlockingTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("starting...");
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        completableFuture.get();
        System.out.println("finished...");
    }
}
