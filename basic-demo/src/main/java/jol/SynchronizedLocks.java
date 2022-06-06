package jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author 996kid@gmail.com
 * @Description SynchronizedLocks
 * @Date 2022/6/2 10:35
 */
public class SynchronizedLocks {

    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        // -XX:BiasedLockingStartupDelay=4000
        Thread.sleep(5000);

        System.out.println(ClassLayout.parseInstance(object).toPrintable());

        synchronized (object) {
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }

        System.out.println(ClassLayout.parseInstance(object).toPrintable());

//        Thread.sleep(100000);
    }
}
