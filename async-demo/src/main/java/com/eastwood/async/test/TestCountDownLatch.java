package com.eastwood.async.test;

import java.util.concurrent.CountDownLatch;

/**
 * @author 996kid
 * @desription
 * @date 2020/1/9
 */
public class TestCountDownLatch {

    static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        Thread t = new Thread(new MyThread(), "myThread");
        t.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

}

class MyThread implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        TestCountDownLatch.countDownLatch.countDown();
    }
}