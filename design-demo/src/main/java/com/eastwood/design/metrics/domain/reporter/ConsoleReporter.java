package com.eastwood.design.metrics.domain.reporter;

import com.eastwood.design.metrics.domain.storage.MetricsStorage;

import java.util.concurrent.ScheduledExecutorService;

/**
 * @ClassName: ConsoleReporter
 * @Description: Console Reporter
 * @Author: yyh
 * @Date: 2023/3/28 17:44
 */
public class ConsoleReporter {

    private final MetricsStorage metricsStorage;

    private final ScheduledExecutorService executor;

    public ConsoleReporter(MetricsStorage metricsStorage, ScheduledExecutorService executor) {
        this.metricsStorage = metricsStorage;
        this.executor = executor;
    }

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {

    }
}
