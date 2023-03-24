package com.eastwood.design.alert.domain;

import com.eastwood.design.alert.domain.handler.AlertRuleHandler;

import java.util.ArrayList;
import java.util.List;

public class Alert {

  List<AlertRuleHandler> rules = new ArrayList<>();

  public void addAlertHandler(AlertRuleHandler alertRuleHandler) {
    rules.add(alertRuleHandler);
  }


//  private AlertRule rule;
//  private Notification notification;
//
//  public Alert(AlertRule rule, Notification notification) {
//    this.rule = rule;
//    this.notification = notification;
//  }

  public void check(ApiInfo apiInfo) {
//    long tps = requestCount / durationOfSeconds;
//    if (tps > rule.getMatchedRule(api).getMaxTps()) {
//      notification.notify(NotificationEmergencyLevel.URGENCY, "");
//    }
//    if (errorCount > rule.getMatchedRule(api).getMaxErrorCount()) {
//      notification.notify(NotificationEmergencyLevel.SEVERE, "");
//    }
    rules.forEach(i -> i.check(apiInfo));
  }
}