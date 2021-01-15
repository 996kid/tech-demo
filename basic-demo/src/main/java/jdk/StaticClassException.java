package jdk;

/**
 * @author 996kid
 * @Description StaticClassException
 * @Date 2021/1/15 10:11
 */
public class StaticClassException {

    static {
        if (true)
        throw new RuntimeException();
    }

    void test() {
        System.out.println("hi");
    }
}
