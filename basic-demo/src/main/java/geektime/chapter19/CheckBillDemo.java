package geektime.chapter19;

import java.util.Vector;
import java.util.concurrent.*;

/** 对账流程优化
 * @author 996kid@gmail.com
 * @Description CheckBillDemo
 * @Date 2022/5/6 20:29
 */
public class CheckBillDemo {

    public void test1() throws InterruptedException {

        while(true/*存在未对账订单*/){
            // 查询未对账订单
            Thread T1 = new Thread(()->{
//                pos = getPOrders();
            });
            T1.start();
            // 查询派送单
            Thread T2 = new Thread(()->{
//                dos = getDOrders();
            });
            T2.start();
            // 等待T1、T2结束
            T1.join();
            T2.join();
            // 执行对账操作
//            diff = check(pos, dos);
            // 差异写入差异库
//            save(diff);
        }
    }

    public void test2() throws InterruptedException {

// 创建2个线程的线程池
        Executor executor =
                Executors.newFixedThreadPool(2);
        while(true/*存在未对账订单*/){
            // 计数器初始化为2
            CountDownLatch latch =
                    new CountDownLatch(2);
            // 查询未对账订单
            executor.execute(()-> {
//                pos = getPOrders();
                latch.countDown();
            });
            // 查询派送单
            executor.execute(()-> {
//                dos = getDOrders();
                latch.countDown();
            });

            // 等待两个查询操作结束
            latch.await();

            // 执行对账操作
//            diff = check(pos, dos);
            // 差异写入差异库
//            save(diff);
        }
    }


    // 订单队列
    Vector<Object> pos;
    // 派送单队列
    Vector<Object> dos;
    // 执行回调的线程池
    Executor executor =
            Executors.newFixedThreadPool(1);
    final CyclicBarrier barrier =
            new CyclicBarrier(2, ()->{
                executor.execute(()->check());
            });

    private void check() {
        Object p = pos.remove(0);
        Object d = dos.remove(0); // 执行对账操作
        Object diff = check(p, d); // 差异写入差异库 
        save(diff);
    }

    private void save(Object diff) {
    }

    private Object check(Object p, Object d) {
        return null;
    }

    public void test3() {
            // 循环查询订单库
            Thread T1 = new Thread(()->{
                while(true/*存在未对账订单*/){
                    // 查询订单库
//                    pos.add(getPOrders());
                    // 等待
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
            T1.start();
            // 循环查询运单库
            Thread T2 = new Thread(()->{
                while(true/*存在未对账订单*/){
                    // 查询运单库
//                    dos.add(getDOrders());
                    // 等待
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
            T2.start();
    }
}
