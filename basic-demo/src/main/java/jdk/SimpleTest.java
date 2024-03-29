package jdk;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static void main(String[] args) throws ParseException {
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

//        String a = "hello world!";
//        System.out.println(a.substring(a.length() - 3));
//
//
//        // Map
//        Map map1 = new ConcurrentHashMap<>();
//        Map map2 = new ConcurrentSkipListMap<>();
//        Map map3 = new HashMap();
//        Map map4 = new TreeMap();
//        Map map5 = new Hashtable();
//        TreeSet treeSet = new TreeSet();



//        User user = new User();
//        user.setName("111");
//        Page<User> page = new Page();
//        page.setObj(user);
//        change(page);
//        System.out.println(page);

//        Integer i = new Integer(4);
//        System.out.println(i.equals(4));

//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY,0);
//        calendar.set(Calendar.MINUTE,0);
//        calendar.set(Calendar.SECOND,0);
//        calendar.add(Calendar.DAY_OF_MONTH,-1);
//        System.out.println(format.format(calendar.getTime()));

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(simpleDateFormat.parse("2023-02-27"));


        Set<Integer> set = new LinkedHashSet<>();
        set.add(5);
        set.add(7);
        set.add(2);
        set.add(4);
        System.out.println(set);
    }

    private static void change(Page<User> page) {
        User user = page.getObj();
        user.setName("2222");
    }


    @Data
    static class Page<T> {
        T obj;
    }

    @Data
    static class User {
        String name;
    }

}
