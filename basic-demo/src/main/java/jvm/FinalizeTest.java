package jvm;

/**
 * @author 996kid@gmail.com
 * @Description FinalizeTest
 * @Date 2022/5/9 21:11
 */
public class FinalizeTest {

    public static void main(String[] args) {
        new FinalizeTest();
        System.gc();

//        System.runFinalization();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        System.out.println("FinalizeTest.finalize");
    }
}
