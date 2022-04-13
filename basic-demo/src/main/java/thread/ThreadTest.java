package thread;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 996kid@gmail.com
 * @Description ThreadTest
 * @Date 2022/3/25 11:45
 */
public class ThreadTest {

    static Object object = new Object();

    public static void main(String[] args) {
        test1();
    }

    // 两个线程交替打印数字字母  1a 2b 3c ~ 26z
    public static void test1() {
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <=26; i++) {
                System.out.println(i);
                try {
                    object.notifyAll();
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                object.wait();
                for (int i = 97; i <= i + 25; i++) {
                    System.out.println((char) i);
                    object.notifyAll();
                    object.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();
    }
}
