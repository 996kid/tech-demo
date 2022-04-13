package jvm;

/**
 * @author 996kid@gmail.com
 * @Description ClassloaderProcess
 * @Date 2022/2/21 22:17
 */
public class ClassloaderProcess {

    private static int i = 10;

    static {
        i = 20;
        j = 30;
//        System.out.println(j);
    }

    private static int j = 20;

    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        ClassloaderProcess classloaderProcess = new ClassloaderProcess();
        int c = classloaderProcess.test1(a, b);
    }

    private int test1(int a, int b) {
        test2(a);
        double d = 10.0;
        long l = 22l;
        return a + b;
    }

    private void test2(int a) {
        System.out.println(a);
    }


    public void test3() {
        int i = 8;
        int j= 10;
        int k = i + j;
    }


    // i++ ++i 的区别


}
