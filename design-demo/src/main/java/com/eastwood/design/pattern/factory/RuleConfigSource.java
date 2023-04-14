package com.eastwood.design.pattern.factory;

import com.eastwood.design.pattern.factory.factorymethod.RuleConfigParserFactoryMap;
import com.eastwood.design.pattern.factory.simplefactory.RuleConfigParserFactory;

public class RuleConfigSource {
  public RuleConfig load(String ruleConfigFilePath) {
    String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
    IRuleConfigParser parser = null;
    if ("json".equalsIgnoreCase(ruleConfigFileExtension)) {
      parser = new JsonRuleConfigParser();
    } else if ("xml".equalsIgnoreCase(ruleConfigFileExtension)) {
      parser = new XmlRuleConfigParser();
    } else if ("yaml".equalsIgnoreCase(ruleConfigFileExtension)) {
      parser = new YamlRuleConfigParser();
    } else if ("properties".equalsIgnoreCase(ruleConfigFileExtension)) {
      parser = new PropertiesRuleConfigParser();
    } else {
      throw new RuntimeException(
             "Rule config file format is not supported: " + ruleConfigFilePath);
    }

    String configText = "";
    //从ruleConfigFilePath文件中读取配置文本到configText中
    RuleConfig ruleConfig = parser.parse(configText);
    return ruleConfig;
  }

  /**
   * 简单工厂模式
   * @param ruleConfigFilePath
   * @return
   */
  public RuleConfig load1(String ruleConfigFilePath) {
    String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
    RuleConfigParserFactory factory = new RuleConfigParserFactory();
    IRuleConfigParser parser = factory.createRuleConfigParser(ruleConfigFileExtension);
    String configText = "";
    //从ruleConfigFilePath文件中读取配置文本到configText中
    RuleConfig ruleConfig = parser.parse(configText);
    return ruleConfig;
  }

  /**
   * 工厂方法模式
   * @param ruleConfigFilePath
   * @return
   */
  public RuleConfig load2(String ruleConfigFilePath) {
    String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
    RuleConfigParserFactoryMap ruleConfigParserFactoryMap = new RuleConfigParserFactoryMap();
    com.eastwood.design.pattern.factory.factorymethod.RuleConfigParserFactory factory = ruleConfigParserFactoryMap.getRuleConfigParserFactory(ruleConfigFileExtension);
    IRuleConfigParser parser = factory.createParser();
    String configText = "";
    //从ruleConfigFilePath文件中读取配置文本到configText中
    RuleConfig ruleConfig = parser.parse(configText);
    return ruleConfig;
  }

  private String getFileExtension(String filePath) {
    //...解析文件名获取扩展名，比如rule.json，返回json
    return "json";
  }
}