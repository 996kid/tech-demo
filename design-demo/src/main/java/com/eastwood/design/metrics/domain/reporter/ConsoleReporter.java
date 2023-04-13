package com.eastwood.design.metrics.domain.reporter;

import com.eastwood.design.metrics.domain.Aggregator;
import com.eastwood.design.metrics.domain.RequestMetrics;
import com.eastwood.design.metrics.domain.RequestStat;
import com.eastwood.design.metrics.domain.storage.MetricsStorage;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
        executor.scheduleAtFixedRate(() -> {
            // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
            long durationInMillis = durationInSeconds * 1000;
            long endTimeInMillis = System.currentTimeMillis();
            long startTimeInMillis = endTimeInMillis - durationInMillis;
            Map<String, List<RequestMetrics>> requestInfos =
                    metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
            Map<String, RequestStat> stats = new HashMap<>();
            for (Map.Entry<String, List<RequestMetrics>> entry : requestInfos.entrySet()) {
                String apiName = entry.getKey();
                List<RequestMetrics> requestInfosPerApi = entry.getValue();
                // 第2个代码逻辑：根据原始数据，计算得到统计数据；
                RequestStat requestStat = Aggregator.aggregate(requestInfosPerApi, durationInMillis);
                stats.put(apiName, requestStat);
            }
            // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）；
            System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMillis + "]");
            Gson gson = new Gson();
            System.out.println(gson.toJson(stats));
        }, 0, periodInSeconds, TimeUnit.SECONDS);
    }
}
