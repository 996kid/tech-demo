package geektime.chapter03;

/** 课后思考： 下面尝试使用 synchronized 修饰代码块来尝试解决并发问题 有何问题
 * @author 996kid@gmail.com
 * @Description Think
 * @Date 2022/4/25 22:03
 */
public class Think {

        // JVM JIT 即时编译器会判断同
    // 步代码块所锁定的对象是否只会被一个线程锁定
    // 如果是，JIT会在编译时取消代码块的同步

        long value = 0L;
        long get() {
            synchronized (new Object()) {
                return value;
            }
        }
        void addOne() {
            synchronized (new Object()) {
                value += 1;
            }
        }
}
