package future;

import java.util.concurrent.CompletableFuture;

/**
 * @author 996kid@gmail.com
 * @Description TransferServicce
 * @Date 2021/7/9 16:58
 */
public interface TransferServicce {

    CompletableFuture transfer(String accountA, String accountB, int money) throws InterruptedException;
}
