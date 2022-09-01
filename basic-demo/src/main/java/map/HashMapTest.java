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
        Map map = new HashMap<>();

        int i = 1 << 4;
        int j = 1 << 16;
        System.out.println(i);
        System.out.println((i - 1) & 35);
        System.out.println(j);

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
//
//        Map hashTable = new Hashtable();
//
//        ThreadLocal threadLocal = new ThreadLocal();
//
//        Lock locl = new ReentrantLock();
    }
}
