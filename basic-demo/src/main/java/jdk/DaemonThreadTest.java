package jdk;

/**
 * The Java Virtual Machine exits when the only threads running are all daemon threads.
 *  JVM会在运行的线程都是守护线程时退出
 * @author 996kid@gmail.com
 * @Description DaemonThreadTest
 * @Date 2022/5/7 11:01
 */
public class DaemonThreadTest {

    public static void main(String[] args) {
//        new Thread(() -> {
//            try {
//                Thread.sleep(50000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("main thread exited");
    }
}
