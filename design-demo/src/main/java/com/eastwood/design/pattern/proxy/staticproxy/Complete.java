package com.eastwood.design.pattern.proxy.staticproxy;

/**
 * @ClassName: Complete
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/19 14:03
 */
public class Complete {

    private final Simple simple;

    public Complete(Simple simple) {
        this.simple = simple;
    }

    void doThings() {
        System.out.println("begin.");
        simple.doThings();
        System.out.println("finish.");
    }
}
