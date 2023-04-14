package com.eastwood.design.pattern.factory.simplefactory;

import com.eastwood.design.pattern.factory.IRuleConfigParser;
import com.eastwood.design.pattern.factory.JsonRuleConfigParser;
import com.eastwood.design.pattern.factory.XmlRuleConfigParser;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: RuleConfigParserFactory
 * @Description: 简单工厂模式 适用于对象初始化步骤简单
 * @Author: yyh
 * @Date: 2023/4/14 14:47
 */
public class RuleConfigParserFactory {

    private static final Map<String, IRuleConfigParser> cachedParser = new HashMap<>(8);

    static {
        cachedParser.put("json", new JsonRuleConfigParser());
        cachedParser.put("xml", new XmlRuleConfigParser());
        //...
    }

    /**
     * @param type
     * @return
     */
    public IRuleConfigParser createRuleConfigParser(String type) {
        if (null == type || type.length() == 0) {
            throw new IllegalArgumentException();
        }
        return cachedParser.get(type);
    }
}
