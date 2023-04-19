package com.eastwood.design.pattern.template;

/**
 * @ClassName: AppChargeChecker
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/19 10:10
 */
public class AppChargeChecker extends AbstractChargeChecker {

    @Override
    protected void stepC() {
        System.out.println("app stepC");
    }

    @Override
    protected void stepB() {
        System.out.println("app stepB");
    }

    @Override
    protected void stepA() {
        System.out.println("app stepA");
    }
}
