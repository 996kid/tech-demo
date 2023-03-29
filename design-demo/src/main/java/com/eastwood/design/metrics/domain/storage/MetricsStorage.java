package com.eastwood.design.metrics.domain.storage;

import com.eastwood.design.metrics.domain.RequestMetrics;

/**
 * @ClassName: MetricsStorage
 * @Description: Metrics Storage
 * @Author: yyh
 * @Date: 2023/3/28 16:06
 */
public interface MetricsStorage {

    /**
     * api 请求指标
     * @param requestMetrics
     */
    void saveMetrics(RequestMetrics requestMetrics);
}
