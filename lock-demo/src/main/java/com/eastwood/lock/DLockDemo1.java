package com.eastwood.lock;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@RestController("/dlock")
public class DLockDemo1 {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    @RequestMapping("/reduceStock")
    public String reduceStock() {
        stringRedisTemplate.opsForValue().setIfAbsent("myLock", "1", 5000, TimeUnit.MILLISECONDS);
        try {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("amount"));
            if (stock > 0) {
                stringRedisTemplate.opsForValue().set("amount", (stock - 1) + "");
            } else {
                return "库存不足";
            }
            return "SUCCESS";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        } finally {
            stringRedisTemplate.delete("myLock");
        }
    }

    @RequestMapping("/reduceStockWithRedisson")
    public String reduceStockWithRedisson() {
        RedissonRedLock redLock = new RedissonRedLock();
        RLock lock = redisson.getLock("lock");
        lock.lock(10, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread().getId());
        try {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("amount"));
            if (stock > 0) {
                stringRedisTemplate.opsForValue().set("amount", (stock - 1) + "");
            } else {
                return "库存不足";
            }
            return "SUCCESS";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        } finally {
            lock.unlock();
        }
    }


    @RequestMapping("/fullStock")
    public void fullStock() {
        stringRedisTemplate.opsForValue().set("amount", "100");
    }
}
