package com.eastwood.design.alert.domain.handler;

import com.eastwood.design.alert.domain.AlertRule;
import com.eastwood.design.alert.domain.ApiInfo;
import com.eastwood.design.alert.domain.Notification;

/**
 * @ClassName: AlertRuleHandler
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/3/22 21:41
 */
public abstract class AlertRuleHandler {

    protected AlertRule rule;
    protected Notification notification;

    public AlertRuleHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public abstract void check(ApiInfo apiInfo);
}
