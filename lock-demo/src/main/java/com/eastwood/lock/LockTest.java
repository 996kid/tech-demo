package com.eastwood.lock;

/**
 * @ClassName LockTest
 * @Description LockTest
 * @Author 996kid
 * @Date 2020/4/2 14:21
 */
public class LockTest {

    public synchronized void aMethod() {
        System.out.println("a Method is called by " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
            System.out.println("lock released ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void bMethod() {
        System.out.println("b Method is called by " + Thread.currentThread().getName());
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();
        new Thread(() -> {
            lockTest.aMethod();
        }, "A Thread").start();
        Thread.sleep(1000);
        System.out.println("main Thread ...");
        new Thread(() -> {
            lockTest.bMethod();
        }, "B Thread").start();
    }
}
