package jol;

import org.openjdk.jol.info.ClassLayout;

/**
 *  java object layout
 *      Java 对象头：
 *           每个 Java 对象都有一个对象头（object header），这个由标记字段和类型指针所构成。
 *           其中，标记字段用以存储 Java 虚拟机有关该对象的运行数据，如哈希码、GC信息以及锁信息，
 *           而类型指针则指向该对象的类。
 * @author yyh
 * @Date 2020/8/13 14:44
 */
public class JolAnalysis implements Runnable{

    private Object object = new Object();

    public static void main(String[] args) {
        Object object = new Object();
        System. out.println(ClassLayout.parseInstance(object).toPrintable());
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
