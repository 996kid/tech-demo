package com.eastwood.design.pattern.template;

/**
 * @ClassName: AbstractChargeChecker
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/19 10:05
 */
public abstract class AbstractChargeChecker implements ChargeChecker {

    /**
     * 通用的充电校验流程  模板方法模式
     */
    public void chargeCheckFlow() {
        stepA();
        stepB();
        stepC();
    }

    protected abstract void stepC();

    protected abstract void stepB();

    protected abstract void stepA();
}
