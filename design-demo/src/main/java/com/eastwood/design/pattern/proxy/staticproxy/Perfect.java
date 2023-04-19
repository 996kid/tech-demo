package com.eastwood.design.pattern.proxy.staticproxy;

/**
 * @ClassName: Perfect
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/19 14:01
 */
public class Perfect extends Simple {

    @Override
    void doThings() {
        System.out.println("begin.");
        super.doThings();
        System.out.println("finish.");
    }
}
