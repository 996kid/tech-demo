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
        thread.start();
        try {
            Thread.sleep(500);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
