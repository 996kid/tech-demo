package geektime.chapter16;

import java.util.concurrent.Semaphore;

/**
 * @author 996kid@gmail.com
 * @Description SemaphoreTest
 * @Date 2022/7/4 18:04
 */
public class SemaphoreTest {
    private static final Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread( () -> run()).start();
        }
    }

    private static void run() {
        try {
            semaphore.acquire();
            System.out.println("业务代码");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
