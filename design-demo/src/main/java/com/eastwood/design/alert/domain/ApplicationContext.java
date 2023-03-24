package com.eastwood.design.alert.domain;

import com.eastwood.design.alert.domain.handler.TpsRuleHandler;

public class ApplicationContext {
  private AlertRule alertRule;
  private Notification notification;
  private Alert alert;

  private static final ApplicationContext instance = new ApplicationContext();

  public static ApplicationContext getInstance() {
    return instance;
  }

  public Alert getAlert() {
    return alert;
  }

  public void initializeBeans() {
    alertRule = new AlertRule(/*.省略参数.*/); //省略一些初始化代码
    notification = new Notification(/*.省略参数.*/); //省略一些初始化代码
    alert = new Alert();
    alert.addAlertHandler(new TpsRuleHandler(alertRule, notification));
//    alert.addAlertHandler(new ErrorAlertHandler(alertRule, notification));
    // 改动三：注册handler
//    alert.addAlertHandler(new TimeoutAlertHandler(alertRule, notification));
  }
  //...省略其他未改动代码...


  public static void main(String[] args) {
      // get alert
    ApplicationContext.getInstance().getAlert().check(new ApiInfo());
  }
}