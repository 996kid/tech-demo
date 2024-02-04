package com.eastwood.design.pattern.template;

/**
 * @ClassName: AbstractChargeChecker
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/19 10:05
 */
public abstract class AbstractChargeChecker implements ChargeChecker {

    /**
     * 依赖a完成某个业务
     *     - ChargeChecker 的子类依赖不同的实现逻辑的A
     *     - 所以需要在子类中初始化a对象
     */
    private A a;

    protected void initA(A a) {
        this.a = a;
    }

    /**
     * 通用的充电校验流程  模板方法模式
     */
    public void chargeCheckFlow() {
        stepA();
        stepB();
        stepC();

        //定制化的a对象
        a.a();
    }

    protected abstract void stepC();

    protected abstract void stepB();

    protected abstract void stepA();
}
