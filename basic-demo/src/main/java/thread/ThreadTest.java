package thread;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/** 交替打印
 * @author 996kid@gmail.com
 * @Description ThreadTest
 * @Date 2022/3/25 11:45
 */
public class ThreadTest {

    private final static PrintUtil2 o = new PrintUtil2();

//    final static Object object2 = new Object();

    public static void main(String[] args) {
        test1();
    }

    // 两个线程交替打印数字字母  1a 2b 3c ~ 26z
    public static void test1() {
        Thread t = new Thread();
        Thread thread1 = new Thread(() -> {
            try {
                o.printNum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                o.printCharacter();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
    }

    // synchronized wait notify
    static class PrintUtil1 {

         synchronized void printNum() throws InterruptedException {
            for (int i = 1; i <=26; i++) {
                System.out.print(i);
                this.notify();
                if (i == 26) {
                    break;
                }
                this.wait();
            }
        }

        synchronized void printCharacter() throws InterruptedException {
            for (int i = 97; i <= 97 + 25; i++) {
                System.out.print((char) i);
                this.notify();
                if (i == 97 + 25) {
                    break;
                }
                this.wait();
            }
        }
    }

    // lock condition await signal
    static class PrintUtil2 {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        void printNum() throws InterruptedException {
            lock.lock();
            for (int i = 1; i <=26; i++) {
                System.out.print(i);
                condition.signal();
                if (i == 26) {
                    break;
                }
                condition.await();
            }
            lock.unlock();
        }

        synchronized void printCharacter() throws InterruptedException {
            lock.lock();
            for (int i = 97; i <= 97 + 25; i++) {
                System.out.print((char) i);
                condition.signal();
                if (i == 97 + 25) {
                    break;
                }
                condition.await();
            }
            lock.unlock();
        }
    }

    static class PrintUtil3 {

        void printNum() throws InterruptedException {
            for (int i = 1; i <=26; i++) {
                System.out.print(i);
            }
        }

        synchronized void printCharacter() throws InterruptedException {
            for (int i = 97; i <= 97 + 25; i++) {
                System.out.print((char) i);
            }
        }
    }
}
