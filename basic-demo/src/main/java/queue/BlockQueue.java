package queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 996kid@gmail.com
 * @Description BlockQueue
 * @Date 2021/12/2 17:15
 */
public class BlockQueue<T> {

    private List queue = new ArrayList(10);

    final Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();

    void enq(T t) {
        lock.lock();
        try {
            while (queue.size() == 10) {

            }
        } finally {
            lock.unlock();
        }
    }

    T deq() {
       return null;
    }


    void test() {
        while (!Thread.interrupted()) {

        }
    }
}
