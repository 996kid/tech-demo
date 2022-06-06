package designpatterns;

/**
 * 单例模式
 * @author 996kid@gmail.com
 * @Description Singleton
 * @Date 2022/6/6 21:21
 */
public class Singleton {

//    private static Singleton singleton = new Singleton();

    //私有化构造器
    private Singleton() {

    }

    private static Singleton singleton;

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public void print() {
        System.out.println("hi");
    }

    public static void main(String[] args) {
        Singleton.getInstance().print();
    }
}
