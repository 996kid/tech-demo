package com.eastwood.design.pattern.factory.factorymethod;

import com.eastwood.design.pattern.factory.IRuleConfigParser;
import com.eastwood.design.pattern.factory.XmlRuleConfigParser;

/**
 * @ClassName: XmlRuleConfigParserFactory
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/14 15:03
 */
public class XmlRuleConfigParserFactory implements RuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new XmlRuleConfigParser();
    }
}
