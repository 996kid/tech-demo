package com.eastwood.design.pattern.template;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ChargeTest
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/19 10:14
 */
public class ChargeTest {

    private static final Map<String, ChargeChecker> checkers = new HashMap<>();

    static {
        checkers.put("app", new AppChargeChecker());
        checkers.put("card", new CardChargeChecker());
    }

    public static void main(String[] args) {
        String type = "app";
        checkers.get(type).chargeCheckFlow();
    }
}
