package singleton;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 996kid@gmail.com
 * @Description SingletonDemo
 * @Date 2021/8/10 16:55
 */
public class SingletonDemo {

    private String name;

    public void hi() {
        System.out.println("hi, " + name);
    }

    SingletonDemo(String name) {
        this.name = name;
    }

    private static SingletonDemo singletonDemo = createSingleton();


    public static SingletonDemo getInstance() {
        return singletonDemo;
    }

    public static SingletonDemo createSingleton() {
        return new SingletonDemo("L3vi");
    }

    public static void main(String[] args) {
        SingletonDemo.getInstance().hi();
        new SimpleDateFormat().format(new Date());
    }
}
