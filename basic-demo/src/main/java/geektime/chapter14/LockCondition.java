package geektime.chapter14;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 996kid@gmail.com
 * @Description LockCondition
 * @Date 2022/4/26 22:26
 */
public class LockCondition {

    /**
     *  Lock 和 synchronized
     * 相比synchronized lock具有的特点：
     // 支持中断的API
     void lockInterruptibly()
     throws InterruptedException;
     // 支持超时的API
     boolean tryLock(long time, TimeUnit unit)
     throws InterruptedException;
     // 支持非阻塞获取锁的API
     boolean tryLock();
     */
    private final Lock rtl = new ReentrantLock();
    int value; public void addOne() {
        // 获取锁
        rtl.lock();
        try { value+=1; }
        finally {
            // 保证锁能释放 rtl.unlock();
            }
    }

}
