package geektime.chapter18;

import java.util.concurrent.locks.StampedLock;

/**
 * @author 996kid@gmail.com
 * @Description Demo
 * @Date 2022/5/5 22:52
 */
public class Demo {

    public static void main(String[] args) {
        final StampedLock sl =
                new StampedLock();

        // 获取/释放悲观读锁示意代码
        long stamp1 = sl.readLock();
        try {
            //省略业务相关代码
        } finally {
            sl.unlockRead(stamp1);
        }

        // 获取/释放写锁示意代码
        long stamp2 = sl.writeLock();
        try {
            //省略业务相关代码
        } finally {
            sl.unlockWrite(stamp2);
        }
    }
}
