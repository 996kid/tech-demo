package jdk;

/**
 * @author 996kid
 * @Description ABC
 * @Date 2021/1/18 10:41
 */
public class ABC {

    public static int a = 1;

    public static void sss() {
        System.out.println(1111);
    }

    static {
        if (true)
            throw new RuntimeException();
    }
}
