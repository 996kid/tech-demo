package com.eastwood.design.pattern.strategy;

import java.util.Arrays;

/**
 * @ClassName: StrategyFactoryWithEnum
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/19 9:45
 */
public enum StrategyFactoryWithEnum implements Strategy {

    STRATEGYA {
        @Override
        public void algorithmInterface() {
            System.out.println("ConcreteStrategyA");
        }
    },
    STRATEGYB {
        @Override
        public void algorithmInterface() {
            System.out.println("ConcreteStrategyB");
        }
    };

    public static StrategyFactoryWithEnum getByType(String type) {
        return Arrays.stream(StrategyFactoryWithEnum.values())
                .filter(e -> e.name().equals(type))
                .findFirst()
                .orElse(StrategyFactoryWithEnum.STRATEGYA);
    }
}
