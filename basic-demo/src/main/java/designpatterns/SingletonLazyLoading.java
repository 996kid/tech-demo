package designpatterns;

/** 懒加载单例   静态内部类的加载与外部类的加载并无关系
 * @author 996kid@gmail.com
 * @Description SingletonLazyLoading
 * @Date 2022/6/6 22:32
 */
public class SingletonLazyLoading {


    private SingletonLazyLoading() {}

    static {
        System.out.println("hello");
    }

    private static class LazyHolder {
        static {
            System.out.println("init...");
        }
        static final SingletonLazyLoading INSTANCE = new SingletonLazyLoading();
    }
    public static SingletonLazyLoading getInstance() {
        return LazyHolder.INSTANCE;
    }


    public static void main(String[] args) {
        SingletonLazyLoading singletonLazyLoading = new SingletonLazyLoading();
    }
}
