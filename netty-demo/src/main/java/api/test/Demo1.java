package api.test;


import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author 996kid@gmail.com
 * @Description Demo1
 * @Date 2021/3/2 16:44
 */
@Slf4j
public class Demo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future future = executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // Callable 有返回结果
//        new Callable<>() {
//            @Override
//            public Object call() throws Exception {
//                return null;
//            }
//        };
        while (!future.isDone()) {
            log.info("{} The task is processing.", new Date());
        }
        log.info("{} The task is done.", new Date());
    }
}
