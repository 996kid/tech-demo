package jdk;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 996kid
 * @Description CollectionTest
 * @Date 2020/12/8 10:28
 */
public class CollectionTest {

    public static void main(String[] args) {
        Set set = new HashSet<User>();
        set.add(new User(1, "2323"));
        set.add(new User(1, "22"));
        System.out.println(set);
    }

    static class User {
        int i;
        String name;

        public User(int i, String name) {
            this.i = i;
            this.name = name;
        }

        @Override
        public int hashCode() {
            return i;
        }

        @Override
        public boolean equals(Object obj) {
            return ((User) obj).i == this.i;
        }

        @Override
        public String toString() {
            return "User{" +
                    "i=" + i +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
