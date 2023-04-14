package com.eastwood.design.metrics.domain.refactor.reporter;

import com.eastwood.design.metrics.domain.RequestMetrics;
import com.eastwood.design.metrics.domain.RequestStat;
import com.eastwood.design.metrics.domain.refactor.Aggregator;
import com.eastwood.design.metrics.domain.refactor.storage.MetricsStorage;
import com.eastwood.design.metrics.domain.refactor.viewer.Viewer;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AbstractReporter
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/14 9:24
 */
public abstract class AbstractReporter implements Reporter {

    protected MetricsStorage metricsStorage;
    protected Aggregator aggregator;
    protected Viewer viewer;

    public AbstractReporter(MetricsStorage metricsStorage, Aggregator aggregator, Viewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }

    protected Map<String, RequestStat> getRequestStat(long endTimeInMillis, long durationInMillis, long startTimeInMillis) {
        Map<String, List<RequestMetrics>> requestInfos =
                metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
        return aggregator.aggregate(requestInfos, durationInMillis);
    }
}
