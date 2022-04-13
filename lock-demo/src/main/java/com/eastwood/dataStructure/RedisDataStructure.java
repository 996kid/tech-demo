package com.eastwood.dataStructure;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.IOException;

/**
 * @author 996kid@gmail.com
 * @Description RedisDataStructure
 * @Date 2021/10/21 13:39
 */
public class RedisDataStructure {

    public static void main(String[] args) throws IOException {
        Config config = Config.fromYAML(RedisDataStructure.class.getClassLoader().getResourceAsStream("redisson.yml"));
        RedissonClient redissonClient = Redisson.create(config);
        RMap<String, Integer> map = redissonClient.getMap("whatever");
        for (int i = 0; i <= 5; i++) {
            Integer j = 0;
            if (map != null) {
                j = map.get("key");
                if (j != null) {
                    j ++;
                } else {
                    j = 1;
                }
            } else {
                i = 1;
            }

        }

    }
}
