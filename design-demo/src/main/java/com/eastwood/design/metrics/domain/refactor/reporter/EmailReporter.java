package com.eastwood.design.metrics.domain.refactor.reporter;

import com.eastwood.design.metrics.domain.RequestMetrics;
import com.eastwood.design.metrics.domain.RequestStat;
import com.eastwood.design.metrics.domain.refactor.Aggregator;
import com.eastwood.design.metrics.domain.refactor.storage.MetricsStorage;
import com.eastwood.design.metrics.domain.refactor.viewer.Viewer;

import java.util.*;

/**
 * @ClassName: EmailReporter
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/13 15:57
 */
public class EmailReporter extends AbstractReporter {

    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, Viewer viewer) {
        super(metricsStorage, aggregator, viewer);
    }


    @Override
    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        Date firstTime = getFirstTime();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long endTimeInMillis = System.currentTimeMillis();
                long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                Map<String, RequestStat> requestStat = getRequestStat(endTimeInMillis, durationInMillis, startTimeInMillis);
                // TODO: 格式化为html格式，并且发送邮件l
                viewer.output(requestStat, startTimeInMillis, endTimeInMillis);
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000);
    }

    private Date getFirstTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
