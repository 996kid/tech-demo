package jol;

import org.openjdk.jol.info.ClassLayout;

/**
 *  java object layout
 * @author yyh
 * @Date 2020/8/13 14:44
 */
public class JolAnalysis implements Runnable{

    private Object object = new Object();

    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        Thread t = new Thread(new JolAnalysis());
        t.start();
    }

    @Override
    public void run() {
        //加锁本质将线程id写入锁对象的对象头中
        synchronized (object) {
            System.out.println("Current thread id: " + Thread.currentThread().getId());
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }
}
