package future;

import java.util.concurrent.CompletableFuture;

/**
 * @author 996kid@gmail.com
 * @Description TransferServiceImpl
 * @Date 2021/7/9 17:03
 */
public class TransferServiceImpl implements TransferServicce {

    private AccountService accountService;

    /**
     *  转账： 给a账户减去100 然后给b账户加上100
     *  同步： 一个线程同步处理两个操作步骤  第一步操作未完成前 线程会等待
     *  异步： 一个线程可以同时处理多个转账操作
     *        第一步操作完成前 线程可以处理其他转账 完成后调用callback方法
     * @param accountA
     * @param accountB
     * @param money
     * @return
     * @throws InterruptedException
     */
    @Override
    public CompletableFuture transfer(String accountA, String accountB, int money) {
        return accountService.add("a", -100)
                .thenCompose(v -> accountService.add("b", 100));
    }
}
