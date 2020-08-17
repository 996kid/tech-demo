package jdk;

/**
 * @author yyh
 * @Description InternStringTest
 * @Date 2020/8/17 9:46
 */
public class InternStringTest {

    public static void main(String[] args) {
//        String a = new String("ab");
//        String b = new String("ab");
//        String c = "ab";
//        String d = "a" + "b";
//        String e = "b";
//        String f = "a" + e;
//
//        System.out.println(c == d);
//        System.out.println(b.intern() == a);
//        System.out.println(b.intern() == c);
//        System.out.println(b.intern() == d);
//        System.out.println(b.intern() == f);
//        System.out.println(b.intern() == a.intern());

        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        //字符常量池中的"1" 和 堆中的"11" 以及中间匿名对象 new String()
        String s3 = new String("1") + new String("1");
        // 将 s3中的“11”字符串放入 String 常量池中，如果此时常量池中不存在“11”字符串
        //jdk1.7后 常量池中不需要再存储一份对象了，可以直接存储堆中的引用。这份引用指向 s3 引用的对象。 也就是说引用地址是相同的
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }
}
