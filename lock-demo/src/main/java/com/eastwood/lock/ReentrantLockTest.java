package com.eastwood.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yyh
 * @Description ReentrantLockTest
 * @Date 2020/8/20 17:15
 * 1. 顺序性规则：对于线程 T1，value+=1 Happens-Before 释放锁的操作 unlock()；
 * 2. volatile 变量规则：由于 state = 1 会先读取 state，所以线程 T1 的 unlock() 操作 Happens-Before 线程 T2 的 lock() 操作；
 * 3. 传递性规则：线程 T1 的 value+=1 Happens-Before 线程 T2 的 lock() 操作。
 */
public class ReentrantLockTest {
    private final Lock rtl =
            new ReentrantLock();
    int value;

    public void addOne() {
        // 获取锁
        rtl.lock();
        try {
            value += 1;
        } finally {
            // 保证锁能释放
            rtl.unlock();
        }
    }


    class X {
        private final Lock rtl =
                new ReentrantLock();
        int value;
        public int get() {
            // 获取锁
            rtl.lock();
            try {
                return value;
            } finally {
                // 保证锁能释放
                rtl.unlock();
            }
        }
        public void addOne() {
            // 获取锁
            rtl.lock();
            try {
                value = 1 + get();
            } finally {
                // 保证锁能释放
                rtl.unlock();
            }
        }
    }

    /**
     *
     // 支持中断的API
     void lockInterruptibly()
     throws InterruptedException;
     // 支持超时的API
     boolean tryLock(long time, TimeUnit unit)
     throws InterruptedException;
     // 支持非阻塞获取锁的API
     boolean tryLock();
     */

    class Account {
        private int balance;
        private final Lock lock
                = new ReentrantLock();
        // 转账
        void transfer(Account tar, int amt){
            while (true) {
                if(this.lock.tryLock()) {
                    try {
                        if (tar.lock.tryLock()) {
                            try {
                                this.balance -= amt;
                                tar.balance += amt;
                            } finally {
                                tar.lock.unlock();
                            }
                        }//if
                    } finally {
                        this.lock.unlock();
                    }
                }//if
            }//while
        }//transfer
    }

/**
    class SampleLock {
        volatile int state;
        // 加锁
        lock() {
            // 省略代码无数
            state = 1;
        }
        // 解锁
        unlock() {
            // 省略代码无数
            state = 0;
        }
    }
 */
}
