package jvm;

/**
 * @author 996kid@gmail.com
 * @Description InvokeDynamicTest
 * @Date 2022/2/24 22:30
 */
public class InvokeDynamicTest {

    public void lambda(Func func) {
         return;
    }

    public static void main(String[] args) {
        InvokeDynamicTest dynamicTest = new InvokeDynamicTest();
        Func func = s -> {
            return "abc";
        };
        Func func1 = new Func() {
            @Override
            public String getStr(String s) {
                return null;
            }
        };

        dynamicTest.lambda(s -> "hello");
    }
}

@FunctionalInterface
interface Func {

    String getStr(String s);
}
