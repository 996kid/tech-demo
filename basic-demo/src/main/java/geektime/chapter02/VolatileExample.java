package geektime.chapter02;

// 以下代码来源于【参考1】

/** chapter02 Java内存模型：看Java如何解决可见性和有序性问题
 *   volatile
 *   happens-before规则
 *  1. 程序的顺序性规则
 *     程序前面对某个变量的修改一定是对后续操作可见的。
 *  2. volatile 变量规则
 *     对一个 volatile 变量的写操作， Happens-Before 于后续对这个 volatile 变量的读操作。
 *  3. 传递性
 *     如果 A Happens-Before B，且 B Happens-Before C，那么 A Happens-Before C。
 *  4. 管程中锁的规则
 *     对一个锁的解锁 Happens-Before 于后续对这个锁的加锁。
 *
 *     课后思考: 有一个共享变量 abc，在一个线程里设置了 abc 的值 abc=3，
 *              你思考一下，有哪些办法可以让其他线程能够看到abc==3？
 */
public class VolatileExample {
  int x = 0;
  volatile boolean v = false;
  public void writer() {
    x = 42;
    v = true;
  }
  public void reader() {
    if (v == true) {
      // 这里x会是多少呢？

    }
  }


//  volatile int abc = 0;

  int abc = 0;
  volatile int state = 0;

  void thinking1() {
    abc = 3;
    state = 1;
  }

  void thinking2() {
    if (state == 1) {
        // abc = 3
    }
  }



  void thinking3() {
    synchronized (this) {
      abc = 3;
    }
  }

  void thinking4() {
    synchronized (this) {
      if (abc == 3) {

      }
    }
  }

}