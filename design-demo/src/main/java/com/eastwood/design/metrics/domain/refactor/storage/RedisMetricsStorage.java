package com.eastwood.design.metrics.domain.refactor.storage;

import com.eastwood.design.metrics.domain.RequestMetrics;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: RedisMetricsStorage
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/3/28 17:51
 */
public class RedisMetricsStorage implements MetricsStorage {

    @Override
    public void saveMetrics(RequestMetrics requestMetrics) {
        System.out.println("save metrics to redis success.");
    }

    @Override
    public List<RequestMetrics> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis) {
        return null;
    }

    @Override
    public Map<String, List<RequestMetrics>> getRequestInfos(long startTimeInMillis, long endTimeInMillis) {
        return null;
    }
}
