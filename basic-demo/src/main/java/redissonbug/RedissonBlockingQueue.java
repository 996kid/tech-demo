package redissonbug;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RBucket;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author 996kid@gmail.com
 * @Description RedissonBlockingQueue
 * @Date 2022/5/11 9:32
 */
public class RedissonBlockingQueue {

    private static RedissonClient redissonClient;

    static {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redissonClient = Redisson.create(config);
    }

    public static void main(String[] args) throws InterruptedException {
        RBlockingQueue rBlockingQueue = redissonClient.getBlockingQueue("bq");
        RBucket<Object> bucket = redissonClient.getBucket("111");
        bucket.get();
        RDelayedQueue rDelayedQueue = redissonClient.getDelayedQueue(rBlockingQueue);
        rDelayedQueue.offer(new Object(), 1, TimeUnit.SECONDS);
        Object obj = rBlockingQueue.take();
    }
}
