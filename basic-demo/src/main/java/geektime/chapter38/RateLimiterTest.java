package geektime.chapter38;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 996kid@gmail.com
 * @Description RateLimiterTest
 * @Date 2022/7/11 20:19
 */
public class RateLimiterTest {

    public static void main(String[] args) {

//限流器流速：2个请求/秒
        RateLimiter limiter =
                RateLimiter.create(2.0);
//执行任务的线程池
        ExecutorService es = Executors
                .newFixedThreadPool(1);
//测试执行20次
        long pre = System.currentTimeMillis();
        for (int i=0; i<20; i++){
            //限流器限流
            limiter.acquire();
            //提交任务异步执行
//            es.execute(()->{
//                long cur=System.nanoTime();
//                //打印时间间隔：毫秒
//                System.out.println(
//                        cur/1000_000);
//            });
            long cur = System.currentTimeMillis();
            System.out.println(cur - pre);
            pre = cur;
        }
    }

}
