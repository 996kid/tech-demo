package jdk;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description AsyncToSync   异步转同步
 * @date 2023/10/24
 */

public class AsyncToSync {

    public static void main(String[] args) {
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            cf.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        get(cf);
    }

    private static void get(CompletableFuture cf) {
    }
}
