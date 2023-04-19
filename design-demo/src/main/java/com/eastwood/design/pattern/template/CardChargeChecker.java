package com.eastwood.design.pattern.template;

/**
 * @ClassName: CardChargeChecker
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/19 10:11
 */
public class CardChargeChecker extends AbstractChargeChecker {
    @Override
    protected void stepC() {
        System.out.println("card stepC");
    }

    @Override
    protected void stepB() {
        System.out.println("card stepB");
    }

    @Override
    protected void stepA() {
        System.out.println("card stepA");
    }
}
