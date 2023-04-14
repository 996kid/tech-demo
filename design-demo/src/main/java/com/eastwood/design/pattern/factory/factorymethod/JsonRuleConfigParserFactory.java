package com.eastwood.design.pattern.factory.factorymethod;

import com.eastwood.design.pattern.factory.IRuleConfigParser;
import com.eastwood.design.pattern.factory.JsonRuleConfigParser;

/**
 * @ClassName: JsonRuleConfigParserFactory
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/14 15:00
 */
public class JsonRuleConfigParserFactory implements RuleConfigParserFactory{
    @Override
    public IRuleConfigParser createParser() {
        return new JsonRuleConfigParser();
    }
}
