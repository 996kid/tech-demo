package map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yyh
 * @Description HashMapTest
 * @Date 2020/8/31 18:30
 */
public class HashMapTest {

    public static void main(String[] args) {
        byte a = 8;
        int b = a;
        int c = a & 0xff;


        Map map = new HashMap<>();
        map.put(null, null);

        int i = 1 << 4;
        int j = 1 << 16;
        System.out.println(i);
        System.out.println((i - 1) & 35);
        System.out.println(j);

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put(null, null);
//
//        Map hashTable = new Hashtable();
//
//        ThreadLocal threadLocal = new ThreadLocal();
//
//        Lock locl = new ReentrantLock();
    }
}
