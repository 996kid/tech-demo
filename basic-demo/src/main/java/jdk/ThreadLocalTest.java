package jdk;

/**
 * @author yyh
 * @Date 2020/8/12 16:18
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set(new Point(1, 2));
        threadLocal.get();
    }

    static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
