package com.eastwood.design.pattern.factory.factorymethod;

import com.eastwood.design.pattern.factory.IRuleConfigParser;

/**
 * @ClassName: RuleConfigParserFactory
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/14 15:01
 */
public interface RuleConfigParserFactory {

    IRuleConfigParser createParser();
}
