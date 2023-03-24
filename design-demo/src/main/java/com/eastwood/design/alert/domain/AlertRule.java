package com.eastwood.design.alert.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: AlertRule
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/3/22 21:05
 */
public class AlertRule {

    private final Map<String, ApiRule> ruleMap = new HashMap<>();


    public ApiRule getMatchedRule(String api) {
        return ruleMap.get(api);
    }
}
