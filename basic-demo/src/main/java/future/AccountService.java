package future;

import java.util.concurrent.CompletableFuture;

/**
 * @author 996kid@gmail.com
 * @Description AccountService
 * @Date 2021/7/9 17:00
 */
public interface AccountService {

    CompletableFuture add(String account, int money);
}
