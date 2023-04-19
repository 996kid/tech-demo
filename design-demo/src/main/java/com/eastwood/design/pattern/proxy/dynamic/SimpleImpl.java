package com.eastwood.design.pattern.proxy.dynamic;

/**
 * @ClassName: SimpleImpl
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/19 14:13
 */
public class SimpleImpl implements Simple {
    @Override
    public void doThings() {
        System.out.println("do simply.");
    }
}
