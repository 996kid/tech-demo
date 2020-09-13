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
    // Lock 和 synchronize的区别
    // Lock 是一个接口，synchronize 是一个关键字
    // 使用synchronize 获取锁，如果获取不到会导致线程阻塞,这也是导致死锁的最大原因
    // Lock 提供了三个api用于解决上述问题
    /**
     *
     * 1,支持中断的API :给阻塞的线程发送中断信号的时候，能够唤醒它，那它就有机会释放曾经持有的锁 A
     void lockInterruptibly()
     throws InterruptedException;
     * 2,支持超时的API, 一段时间内没有获取到锁，抛出异常
     boolean tryLock(long time, TimeUnit unit)
     throws InterruptedException;
     * 3,支持非阻塞获取锁的API，如果尝试获取锁失败，并不进入阻塞状态，而是直接返回
     boolean tryLock();
     */
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
                                break;
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
