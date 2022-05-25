package thread;


/**
 * @author 996kid@gmail.com
 * @Description ThreadTest
 * @Date 2022/3/25 11:45
 */
public class ThreadTest {

    private final static PrintUtil o = new PrintUtil();

    // 为0时打印数字 为1时打印字母
    volatile static int state = 0;

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

    static class PrintUtil {

         synchronized void printNum() throws InterruptedException {
            for (int i = 1; i <=26; i++) {
                System.out.print(i);
                this.notify();
                this.wait();
            }
        }

        synchronized void printCharacter() throws InterruptedException {
            for (int i = 97; i <= 97 + 25; i++) {
                System.out.print((char) i);
                this.notify();
                this.wait();
            }
        }
    }
}
