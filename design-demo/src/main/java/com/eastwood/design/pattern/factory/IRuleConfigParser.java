package com.eastwood.design.pattern.factory;

/**
 * @ClassName: IRuleConfigParser
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/14 14:43
 */
public interface IRuleConfigParser {
    RuleConfig parse(String configText);
}
