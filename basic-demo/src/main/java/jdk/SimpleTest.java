package jdk;

/**
 * @author 996kid
 * @Description SimpleTest
 * @Date 2020/12/9 16:42
 */
public class SimpleTest {

    public static void main(String[] args) {
//        int a, b;
//        a = b = 6;
//        System.out.println(a + "" + b);

//        int r = 9, s = 8;
//        double t = new BigDecimal(9).divide(new BigDecimal(8), 2, BigDecimal.ROUND_HALF_UP)
//                .multiply(new BigDecimal(100)).doubleValue();
//        System.out.println(t);

        try {
            StaticClassException staticClassException = new StaticClassException();
            staticClassException.test();
        } catch (Throwable e) {
            // 这里必须是catch Throwable
            System.out.println("error");
        }
    }

}
