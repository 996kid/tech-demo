package jdk;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 996kid
 * @Description SimpleTest
 * @Date 2020/12/9 16:42
 */
public class SimpleTest {

    public static void main(String[] args) {
//        int a, b;
//        a = b = 6;
//        System.out.println(a + "" + b);

//        int r = 9, s = 8;
//        double t = new BigDecimal(9).divide(new BigDecimal(8), 2, BigDecimal.ROUND_HALF_UP)
//                .multiply(new BigDecimal(100)).doubleValue();
//        System.out.println(t);

//        try {d
//            StaticClassException staticClassException = new StaticClassException();
//            staticClassException.test();
//        } catch (Throwable e) {
//            // 这里必须是catch Throwable. catch exception catch 不到
//             System.out.println("error");
//        }

        // 第一次初始化类时在静态代码块里面抛出异常
        // 导致第二次引用该类的静态成员时抛出 NoClassDefFoundError
//        try {
//            ABC abc = new ABC();
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        System.out.println(ABC.a);


//        Random random = new Random();
//        System.out.println(random.nextInt(2));

//        int a = 10;
//        if (a > 9) {
//            System.out.println(a);
//            a = 20;
//        } else if (a > 19) {
//            System.out.println(a);
//        }
//        Integer i = null;
//        i = 10;
//        System.out.println(i);

//        Lock lock = new ReentrantLock();

        String a = "hello world!";
        System.out.println(a.substring(a.length() - 3));


        // Map
        Map map1 = new ConcurrentHashMap<>();
        Map map2 = new ConcurrentSkipListMap<>();
        Map map3 = new HashMap();
        Map map4 = new TreeMap();
        Map map5 = new Hashtable();
        TreeSet treeSet = new TreeSet();
    }

}
