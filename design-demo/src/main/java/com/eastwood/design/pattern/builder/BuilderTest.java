package com.eastwood.design.pattern.builder;

/**
 * @ClassName: BuilderTest
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/19 13:34
 */
public class BuilderTest {

    /**
     * 建造者模式
     *     - 可以在build方法里做成员变量的校验
     *     - Hero成员变量不再暴露set方法，防止变量在使用中随意修改
     * @param args
     */
    public static void main(String[] args) {
        Hero hero = new Hero.Builder().withProfession("warrior").build();
    }
}
