package jvm;

/**
 * @author 996kid@gmail.com
 * @Description StringInternTEst
 * @Date 2022/3/10 21:59
 */
public class StringInternTest {

    public static void main(String[] args) {
        // debug here
//        System.out.println("");
//        System.out.println("1");
//        System.out.println("2");
//        System.out.println("3");
//        System.out.println("4");
//
//        System.out.println("1");
//        System.out.println("2");
//        System.out.println("3");
//        System.out.println("4");

        test4();
    }


    public static void test1() {
        String s1 = "hello";
        String s2 = "world";
        String s3 = "hello" + "world";
        String s4 = s1 + "world";
        String s5 = "hello" + s2;
        String s6 = s1 + s2;
        String s7 = "helloworld";
        System.out.println(s3 == s7); // true
        System.out.println(s3 == s6); // false
        System.out.println(s3 == s4); // false
        System.out.println(s3 == s5); // false
//        try {
//            Thread.sleep(1000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void test2() {
        String s1 = "world";
        String s2 = new String("world");
        String s3 = s2.intern();

        System.out.println(s1 == s2);//false
        System.out.println(s1 == s3);//true
        System.out.println(s2 == s3);//false
    }

    public static void test3() {
        // 创建两个对象 1.字符串常量池中的字面量 2.堆中的new String
        String s1 = new String("abc");
        s1.intern();
        String s2 = "abc";
        System.out.println(s1 == s2); //false
    }

    public static void test4() {
        // 不会在字符串常量池中生成 abc字面量
        String s1 = new String("ab") + new String("c");
        // 如果字符串常量池没有则新建返回引用， 有则直接返回引用； 这里没有但是堆区中存在“abc”,
        //会优化为新建一个直接指向堆区的引用
        s1.intern();
        String s2 = "abc";
        System.out.println(s1 == s2);//true
    }

    // intern 空间效率测试
    public static void test5() {
        String[] array = new String[1000 * 1000];
        String[] exist = new String[] {"1", "2", "3", "4", "5", "6"};

        for (int i =0; i < array.length; i ++) {
//            array[i] = new String(String.valueOf(i % exist.length));
            array[i] = new String(String.valueOf(i % exist.length)).intern();
        }

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
