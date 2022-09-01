package leetcode.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class FooBar {
    private int n;



    private Semaphore semaphore1 = new Semaphore(0);
    private Semaphore semaphore2 = new Semaphore(1);



    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private volatile boolean flag = false;

    public FooBar(int n) {
        this.n = n;
    }


    public static void main(String[] args) {
        FooBar fooBar = new FooBar(10);

        new Thread(() -> {
            try {
                fooBar.foo1(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fooBar.bar1(() -> System.out.print("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public void foo1(Runnable printFoo) throws InterruptedException {
        while (!flag);

        synchronized (this) {
            for (int i = 0; i < n; i++) {

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                this.notifyAll();
                if (i < n - 1) {
                    this.wait();
                }
            }
        }
    }

    public void bar1(Runnable printBar) throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < n; i++) {
                flag = true;
                this.wait();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                this.notifyAll();
            }
        }
    }

    public void foo2(Runnable printFoo) throws InterruptedException {
        try {
            lock.lock();
            for (int i = 0; i < n; i++) {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                flag = true;
                condition.signalAll();

                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public void bar2(Runnable printBar) throws InterruptedException {
        // 自旋等待条件满足
        while (!flag) {

        }
        try {
            lock.lock();
            for (int i = 0; i < n; i++) {
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                condition.signalAll();
                if (i < n - 1)
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }


    public void foo3(Runnable printFoo) throws InterruptedException {

        semaphore2.acquire();
        printFoo.run();
        semaphore1.release();
    }


    public void bar3(Runnable printBar) throws InterruptedException {
        semaphore1.acquire();
        printBar.run();
        semaphore2.release();
    }
}