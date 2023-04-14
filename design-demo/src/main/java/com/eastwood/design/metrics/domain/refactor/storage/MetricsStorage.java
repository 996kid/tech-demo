package com.eastwood.design.metrics.domain.refactor.storage;

import com.eastwood.design.metrics.domain.RequestMetrics;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取指定api的请求指标信息
     * @param apiName
     * @param startTimeInMillis
     * @param endTimeInMillis
     * @return
     */
    List<RequestMetrics> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis);

    /**
     * 获取所有api的请求指标信息
     * @param startTimeInMillis
     * @param endTimeInMillis
     * @return
     */
    Map<String, List<RequestMetrics>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);
}
