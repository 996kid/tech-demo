package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author 996kid@gmail.com
 * @date 2024/3/14
 */
public class CompletableFutureTest2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture cf = new CompletableFuture();
        new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cf.complete("2222");
        }).start();
        System.out.println(cf.get());
        System.out.println("11111");
    }
}
