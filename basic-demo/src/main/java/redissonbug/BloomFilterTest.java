package redissonbug;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author 996kid@gmail.com
 * @Description BloomFilter
 * @Date 2022/7/12 15:29
 */
public class BloomFilterTest {

    private static RedissonClient redissonClient;

    static {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redissonClient = Redisson.create(config);
    }

    public static void main(String[] args) {
        RBloomFilter bloomFilter = redissonClient.getBloomFilter("custom");
        // 初始化布隆过滤器，预计统计元素数量为55000000，期望误差率为0.03
        bloomFilter.tryInit(55000000L, 0.03);
        bloomFilter.add(new A("111", 1));
        bloomFilter.add(new A("222", 2));
        System.out.println(bloomFilter.contains(new A("111", 2)));
        System.out.println(bloomFilter.contains(new A("111", 1)));
    }

    static class A {
        private String name;

        private int age;

        A(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
