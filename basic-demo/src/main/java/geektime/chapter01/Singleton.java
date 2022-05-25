package geektime.chapter01;


/** 编译优化带来的有序性问题:
 * 以为的情况。
 * 1.分配一块内存 M；
 * 2.在内存 M 上初始化 Singleton 对象；
 * 3.然后 M 的地址赋值给 instance 变量。
 * 但是实际上优化后的执行路径却是这样的：
 * 1.分配一块内存 M；
 * 2.将 M 的地址赋值给 instance 变量；
 * 3.最后在内存 M 上初始化 Singleton 对象。
 */
public class Singleton {
  static Singleton instance;
  static Singleton getInstance(){
    if (instance == null) {
      synchronized(Singleton.class) {
        if (instance == null)
          instance = new Singleton();
        }
    }
    return instance;
  }
}