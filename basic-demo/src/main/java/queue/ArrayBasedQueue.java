package queue;

import java.util.ArrayList;

/**
 * @author 996kid@gmail.com
 * @Description ArrayBasedQueueDemo
 * @Date 2022/4/12 22:59
 */
public class ArrayBasedQueue {

    private int[] queue;

    // 实际元素个数
    private int size;

    // 有界队列
    ArrayBasedQueue(int length) {
        queue = new int[length];
    }

    public synchronized void add(int v) {
        if (size == queue.length) {
            // full 扩容
            ensureCapacity();
        }
        queue[size++] = v;
    }


    public synchronized int take() {
        if (size <= 0) {
            // empty
            throw new RuntimeException("empty queue");
        } else {
            int element = queue[0];
            return element;
        }
    }

    private void ensureCapacity() {
        queue = new int[2 * queue.length + 1];
    }
}
