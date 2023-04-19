package com.eastwood.design.pattern.proxy.staticproxy;

/**
 * @ClassName: InheritStaticProxy
 * @Description: 继承实现的静态代理
 * @Author: yyh
 * @Date: 2023/4/19 13:57
 */
public class InheritStaticProxy {

    public static void main(String[] args) {
        // 继承实现的静态代理
        Perfect perfect = new Perfect();
        perfect.doThings();

        // 组合实现的静态代理
        Complete complete = new Complete(new Simple());
        complete.doThings();
    }
}
