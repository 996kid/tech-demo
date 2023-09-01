package com.eastwood.design.pattern.factory.simplefactory;

import com.eastwood.design.pattern.factory.IRuleConfigParser;
import com.eastwood.design.pattern.factory.RuleConfig;

import java.util.Arrays;

/**
 * @ClassName: EnumFactory
 * @Description: EnumFactory
 * @Author: yyh
 * @Date: 2023/4/17 13:18
 */
public enum EnumFactory implements IRuleConfigParser {

    /**
     * 枚举类的实质
     *  JSON 是枚举类EnumFactory的一个实例
     *
     */
    JSON {
        @Override
        public RuleConfig parse(String configText) {
            System.out.println("json parser");
            return null;
        }
    },

    XML {
        @Override
        public RuleConfig parse(String configText) {
            System.out.println("xml parser");
            return null;
        }
    };

    public static EnumFactory getByType(String type) {
        return Arrays.stream(EnumFactory.values())
                .filter(enumFactory -> enumFactory.name().equals(type))
                .findFirst().orElse(EnumFactory.JSON);
    }

    public static void main(String[] args) {
        EnumFactory.getByType("XML").parse("lol");
    }
}
