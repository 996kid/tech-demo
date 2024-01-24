package jvm;

/**
 * @author 996kid@gmail.com
 * @Description HeapDemo
 * @Date 2022/2/27 20:48
 */
public class HeapDemo {

    public static void main(String[] args) {
        System.out.println("main start...");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end...");
    }
}
