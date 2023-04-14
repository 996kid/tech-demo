package com.eastwood.design.pattern.factory.factorymethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: RuleConfigParserFactoryMap
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/14 15:06
 */
public class RuleConfigParserFactoryMap {

    private static final Map<String, RuleConfigParserFactory> factoryMapping = new HashMap<>(4);

    static {
        factoryMapping.put("json", new JsonRuleConfigParserFactory());
        factoryMapping.put("xml", new XmlRuleConfigParserFactory());
        //...
    }

    public RuleConfigParserFactory getRuleConfigParserFactory(String type) {
        if (null == type || type.length() == 0) {
            throw new IllegalArgumentException();
        }
        return factoryMapping.get(type);
    }
}
