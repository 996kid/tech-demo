package com.eastwood.design.pattern.template;

/**
 * @ClassName: CallbackTemplateTest
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/19 10:33
 */
public class CallbackTemplateTest {

    /**
     * 类似JDBCTemplate
     * callback 模板
     * @param callback
     */
    public void test(Callback callback) {
        callback.a();
        System.out.println("world");
    }

    @FunctionalInterface
    interface Callback {
        void a();
    }

    public static void main(String[] args) {
        CallbackTemplateTest callbackTemplateTest = new CallbackTemplateTest();
        callbackTemplateTest.test(() -> System.out.print("hello, "));
    }
}
