package com.eastwood.lock;

/**
 * @ClassName LockTest
 * @Description LockTest
 * @Author 996kid
 * @Date 2020/4/2 14:21
 */
public class LockTest {
    //模拟转账锁定两个账户
    //分别锁账户
    private Object account1 = new Object();

    private Object account2 = new Object();


    public void aMethod() {
        synchronized (account1) {
            System.out.println(Thread.currentThread().getName() + " locked account1");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (account2) {
                System.out.println(Thread.currentThread().getName() + " locked account2");
            }
        }
    }

    public void bMethod() {
        synchronized (account2) {
            System.out.println(Thread.currentThread().getName() + " locked account2");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (account1) {
                System.out.println(Thread.currentThread().getName() + " locked account1");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();
        new Thread(() -> {
            lockTest.aMethod();
        }, "A Thread").start();
        new Thread(() -> {
            lockTest.bMethod();
        }, "B Thread").start();
    }
}
