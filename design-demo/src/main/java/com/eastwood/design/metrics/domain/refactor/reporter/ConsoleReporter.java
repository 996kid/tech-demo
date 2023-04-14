package com.eastwood.design.metrics.domain.refactor.reporter;

import com.eastwood.design.metrics.domain.RequestMetrics;
import com.eastwood.design.metrics.domain.RequestStat;
import com.eastwood.design.metrics.domain.refactor.Aggregator;
import com.eastwood.design.metrics.domain.refactor.storage.MetricsStorage;
import com.eastwood.design.metrics.domain.refactor.viewer.Viewer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ConsoleReporter
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/13 15:56
 */
public class ConsoleReporter extends AbstractReporter {

    private final ScheduledExecutorService executor;

    public ConsoleReporter(MetricsStorage metricsStorage, ScheduledExecutorService executor, Aggregator aggregator, Viewer viewer) {
        super(metricsStorage, aggregator, viewer);
        this.executor = executor;
    }

    @Override
    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(() -> {
            // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
            long durationInMillis = durationInSeconds * 1000;
            long endTimeInMillis = System.currentTimeMillis();
            long startTimeInMillis = endTimeInMillis - durationInMillis;
            Map<String, RequestStat> requestStat = getRequestStat(endTimeInMillis, durationInMillis, startTimeInMillis);
            viewer.output(requestStat, startTimeInMillis, endTimeInMillis);
        }, 0, periodInSeconds, TimeUnit.SECONDS);
    }
}
