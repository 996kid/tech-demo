package delayedqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author 996kid@gmail.com
 * @Description JavaBasedDelayQueueDemo
 * @Date 2021/8/27 15:43
 */
public class JavaBasedDelayQueueDemo {

    private static DelayQueue<DelayedItem> delayQueue = new DelayQueue<>();

    public static void main(String[] args) throws InterruptedException {
        delayQueue.add(
                new JavaBasedDelayQueueDemo.DelayedItem("aaa", System.currentTimeMillis() + 5000));
        delayQueue.add(
                new JavaBasedDelayQueueDemo.DelayedItem("bbb", System.currentTimeMillis() + 10000));
        System.out.println(delayQueue.take().getItemInfo());
        System.out.println(delayQueue.take().getItemInfo());
    }
    static class DelayedItem implements Delayed {


        private String itemInfo;

        private long millisecond;

        public DelayedItem(String itemInfo, long millisecond) {
            this.itemInfo = itemInfo;
            this.millisecond = millisecond;
        }

        public String getItemInfo() {
            return itemInfo;
        }

        public void setItemInfo(String itemInfo) {
            this.itemInfo = itemInfo;
        }

        public long getMillisecond() {
            return millisecond;
        }

        public void setMillisecond(long millisecond) {
            this.millisecond = millisecond;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return millisecond - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) == o.getDelay(TimeUnit.MILLISECONDS)) {
                return 0;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
