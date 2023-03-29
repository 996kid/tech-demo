package com.eastwood.design.metrics.domain.reporter;

import com.eastwood.design.metrics.domain.storage.MetricsStorage;

import java.util.concurrent.ScheduledExecutorService;

/**
 * @ClassName: EmailReporter
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/3/28 17:49
 */
public class EmailReporter {

    private final MetricsStorage metricsStorage;

    private final ScheduledExecutorService executor;

    public EmailReporter(MetricsStorage metricsStorage, ScheduledExecutorService executor) {
        this.metricsStorage = metricsStorage;
        this.executor = executor;
    }
}
