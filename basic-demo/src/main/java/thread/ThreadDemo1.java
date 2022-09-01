package thread;

/**
 * @author 996kid@gmail.com
 * @Description ThreadDemo
 * @Date 2022/5/16 11:01
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
         Thread thread = new Thread(() -> {
             try {
                 System.out.println(System.currentTimeMillis());
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 System.out.println(System.currentTimeMillis());
                 System.out.println("Interrupted by main thread");
                 e.printStackTrace();
             }
             System.out.println("started..");
         });
         //守护线程   没有非守护线程的时候 JVM退出进程
        thread.setDaemon(true);
        thread.start();
        // 让出CPU使用权
        Thread.yield();
//        try {
//            Thread.sleep(500);
//            thread.interrupt();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("hello");


    }
}
