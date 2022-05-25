package geektime.chapter03;

/**
 *
 */
public class X {
  // 修饰非静态方法 this
  synchronized void foo() {
    // 临界区
  }
  // 修饰静态方法 X.class
  synchronized static void bar() {
    // 临界区
  }

  // 修饰代码块
  Object obj = new Object();
  void baz() {
    synchronized(obj) {
      // 临界区
    }
  }
}  