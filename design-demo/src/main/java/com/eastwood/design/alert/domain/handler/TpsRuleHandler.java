package com.eastwood.design.alert.domain.handler;

import com.eastwood.design.alert.domain.AlertRule;
import com.eastwood.design.alert.domain.ApiInfo;
import com.eastwood.design.alert.domain.Notification;
import com.eastwood.design.alert.domain.NotificationEmergencyLevel;

/**
 * @ClassName: TpsRuleHandler
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/3/22 21:41
 */
public class TpsRuleHandler extends AlertRuleHandler {

    public TpsRuleHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    public void check(ApiInfo apiInfo) {
        long tps = apiInfo.getTps();
        if (tps > rule.getMatchedRule(apiInfo.getApi()).getMaxTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "");
        }
    }
}
