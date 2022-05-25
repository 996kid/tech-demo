package geektime.chapter14;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 你已经知道 tryLock() 支持非阻塞方式获取锁，
 * 下面这段关于转账的程序就使用到了 tryLock()，你来看看，它是否存在死锁问题呢？
 *  活锁
 * @author 996kid@gmail.com
 * @Description Think
 * @Date 2022/4/26 22:33
 */
public class Account {

        private int balance;
        private final Lock lock
                = new ReentrantLock();
        // 转账
        void transfer(Account tar, int amt){
            while (true) {
                if(this.lock.tryLock()) {
                    try {
                        if (tar.lock.tryLock()) {
                            try {
                                this.balance -= amt;
                                tar.balance += amt;
                            } finally {
                                tar.lock.unlock();
                            }
                        }//if
                    } finally {
                        this.lock.unlock();
                    }
                }//if
            }//while
        }//transfer

}
