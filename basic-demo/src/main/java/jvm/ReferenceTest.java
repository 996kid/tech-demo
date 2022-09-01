package jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author 996kid@gmail.com
 * @Description ReferenceTest
 * @Date 2022/5/9 21:18
 */
public class ReferenceTest {

    private static final int _10M = 1024 * 1024 * 10;

    private static final int _12M = 1024 * 1024 * 12;

    public static void main(String[] args) {
        Reference reference;
        SoftReference softReference;
        WeakReference weakReference;
        PhantomReference phantomReference;
    }


    // VM options: -Xms20m -Xmx20m
    private void softReferenceDemo() {
        SoftReference<Byte[]> softReference = new SoftReference<>(new Byte[_10M]);
        System.out.println(softReference.get());
        System.gc();
        try {
            // make sure GC thread complete
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(softReference.get());
        // 堆内存不足时回收软引用
        Byte[] bytes = new Byte[_12M];
        System.out.println(softReference.get());
    }

    class A {

        // GC
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println();
        }
    }

    private void weakReferenceDemo() throws InterruptedException {
        WeakReference<A> weakReference = new WeakReference<>(new A());
        System.out.println(weakReference.get());
        // 遇到gc就会回收
        System.gc();
        Thread.sleep(1000);
        System.out.println(weakReference.get());
    }
}
