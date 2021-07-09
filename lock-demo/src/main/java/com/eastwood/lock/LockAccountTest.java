package com.eastwood.lock;

/**
 * @ClassName LockTest
 * @Description LockTest
 * @Author 996kid
 * @Date 2020/4/2 14:21
 */
public class LockAccountTest {
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
        //死锁测试
        LockAccountTest lockTest = new LockAccountTest();
        new Thread(() -> {
            lockTest.aMethod();
        }, "A Thread").start();
        new Thread(() -> {
            lockTest.bMethod();
        }, "B Thread").start();
//         synchronized(A.class)
//        A a1 = new LockAccountTest.A();
//        A a2 = new LockAccountTest.A();
//        new Thread(() -> {
//             a1.hi();
//         }).start();
//        System.out.println("main thread running");
//        new Thread(() -> {
//            a2.hello();
//        }).start();
    }



    static class A {
        void hi() {
            synchronized (A.class) {
                System.out.println("hi");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        void hello() {
            synchronized (A.class) {
                System.out.println("hello");
            }
        }
    }
}
