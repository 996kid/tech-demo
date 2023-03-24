package com.eastwood.lock;

import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.Executors;

/**
 * @ClassName DistributionLockTest
 * @Description DistributionLockTest redis分布式锁
 * @Author 996kid
 * @Date 2020/4/29 10:36
 */
public class DistributionLockTest {

    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final long ACQUIRE_TIMEOUT = 5000;
    private static final String LOCK_KEY = "LOCK";
    private Jedis jedis = new Jedis();

    /**
     *  加锁
     *  1.互斥性。在任意时刻，只有一个客户端能持有锁。 redis原子set
     *  2.不会发生死锁。即使有一个客户端在持有锁的期间崩溃而没有主动解锁，
     *    也能保证后续其他客户端能加锁。 设置过期时间
     *  3.具有容错性。只要大部分的Redis节点正常运行，客户端就可以加锁和解锁。
     *  4.加锁和解锁必须是同一个客户端。 线程获取锁时 设置唯一id
     */
    public String tryLock() {
        try {
            // 获取锁的超时时间，超过这个时间则放弃获取锁
            long end = System.currentTimeMillis() + ACQUIRE_TIMEOUT;
            // 随机生成一个 value
            String requireToken = UUID.randomUUID().toString();
            while (System.currentTimeMillis() < end) {
                String result = jedis
                        .set(LOCK_KEY, requireToken, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, 10000);
                if (LOCK_SUCCESS.equals(result)) {
                    return requireToken;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (Exception e) {
            System.out.println("acquire lock due to error");
        }

        return null;
    }

    /**
     *  释放锁
     */
    public Boolean tryReleaseLock(String identify) {
        if (identify == null) {
            return false;
        }

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = new Object();
        try {
            result = jedis.eval(script, Collections.singletonList(LOCK_KEY),
                    Collections.singletonList(identify));
            if (RELEASE_SUCCESS.equals(result)) {
                System.out.println("release lock success, requestToken:{}");
                return true;
            }
        } catch (Exception e) {
            System.out.println("release lock due to error");
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        System.out.println("release lock failed");
        return false;
    }


    public static void main(String[] args) {
        String ss = "aaa";
        StringBuilder sb = new StringBuilder("bbb");
        change(ss);
        changeSb(sb);
        System.out.println(ss);
        System.out.println(sb);
    }

    private static void changeSb(StringBuilder sb) {
        sb.append("ccc");
    }

    static void change(String s) {
        s = "abc";
    }
}
