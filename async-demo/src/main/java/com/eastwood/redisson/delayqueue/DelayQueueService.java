package com.eastwood.redisson.delayqueue;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 996kid@gmail.com
 * @Description DelayQueueService   基于redisson的延迟队列本质： 使用HashedWheelTimer延迟放入阻塞队列
 * @Date 2022/2/28 15:56
 */
public class DelayQueueService {
    
    @Resource
    private RedissonClient redissonClient;
    
    private RDelayedQueue<Task> redissonDelayedQueue;

    private RBlockingQueue<Task> redissonBlockingQueue;

    @PostConstruct
    public void init() {
        redissonBlockingQueue = redissonClient.getBlockingQueue("task");
        redissonDelayedQueue = redissonClient.getDelayedQueue(redissonBlockingQueue);
    }

    /** delayqueue 执行
     * local expiredValues = redis.call('zrangebyscore',
     * KEYS[2], 0, ARGV[1], 'limit', 0, ARGV[2]); # 返回最多100个score处在区间0~当前时间戳中的有序的timeoutset中的元素
     * if #expiredValues > 0 then  # 如果存在遍历存入阻塞队列中
     * for i, v in ipairs(expiredValues) do
     * local randomId, value = struct.unpack('dLc0', v);
     * redis.call('rpush', KEYS[1], value);
     * redis.call('lrem', KEYS[3], 1, v);
     * end;
     * redis.call('zrem', KEYS[2], unpack(expiredValues));
     * end;
     * local v = redis.call('zrange', KEYS[2], 0, 0, 'WITHSCORES');
     * if v[1] ~= nil then
     * return v[2];
     * end
     * return nil;
     * keys: getRowName() (自己指定的名字),timeoutSetName, queueName
     * ARGV: System.currentTimeMillis(), 100
     */




    /** 添加延迟任务lua脚本：
     * local value = struct.pack('dLc0', tonumber(ARGV[2]), string.len(ARGV[3]), ARGV[3]);
     * redis.call('zadd', KEYS[2], ARGV[1], value); # 添加到SortedSet中
     * redis.call('rpush', KEYS[3], value); # 添加到list
     * local v = redis.call('zrange', KEYS[2], 0, 0); # 获取sortedset 分数 0~0区间的元素集合
     * if v[1] == value then
     * redis.call('publish', KEYS[4], ARGV[1]); # 如果当前元素是第一个需要被执行的任务，则发布该元素的timeout到channel中
     * end;
     * keys: getRawName(), timeoutSetName, queueName, channelName
     * ARGV: timeout, randomId, encode(e)
     *     以timeout为score的SortedSet
     *     list
     *     channel 发布timeout
     * @param task
     */
    public void put(Task task) {
        redissonDelayedQueue.offer(task, 2000, TimeUnit.SECONDS);
    }

    public void remove(Task task) {
        redissonDelayedQueue.remove();
    }

    public Task take() throws InterruptedException {
        return redissonBlockingQueue.take();
    }

    class Task {
        private String item;
    }
}
