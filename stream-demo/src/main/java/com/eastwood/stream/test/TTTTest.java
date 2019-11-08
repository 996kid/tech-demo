package com.eastwood.stream.test;

import com.eastwood.stream.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author 996kid
 * @desription
 * @date 2019/11/7
 */
public class TTTTest {

    private  List<User> users;

    @Before
    public void init() {
        users = new ArrayList<>();
        User user1 = new User("eastwood", "123456", 18);
        User user2 = new User("flybeef", "123456", 19);
        User user3 = new User("kid", "123456", 20);
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    @Test
    public void test() {
        Stream stream = users.stream();
    }

}
