package geektime.chapter01;


/**
 * 课后思考： 在 32 位的机器上对 long 型变量进行加减操作存在并发隐患
 *  long 占64位 加减操作需要多条指令 不能保证原子操作
 */
public class Test {
    private long count = 0;
    private void add10K() {
        int idx = 0;
        while(idx++ < 10000) {
            count += 1;
        }
    }

    /**
     * 1. CPU缓存导致的可见性问题
     * 2. 线程切换导致的原子性问题
     * 3. 编译优化带来的有序性问题
     * @return
     * @throws InterruptedException
     */
    public static long calc() throws InterruptedException {
        final Test test = new Test();
        // 创建两个线程，执行add()操作
        Thread th1 = new Thread(()->{
            test.add10K();
        });
        Thread th2 = new Thread(()->{
            test.add10K();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        return test.count;
    }




    public static void main(String[] args) throws InterruptedException {
        System.out.println(calc());
    }
}