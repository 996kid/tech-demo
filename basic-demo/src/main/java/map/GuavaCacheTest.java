package map;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author 996kid@gmail.com
 * @date 2024/3/14
 */
public class GuavaCacheTest {

    public static void main(String[] args) throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES).build();
        cache.put("111", "222");
        System.out.println(cache.asMap().get("111"));
        Thread.sleep(65000);
        System.out.println(cache.asMap().get("111"));
    }
}
