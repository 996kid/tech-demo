package com.eastwood.design.metrics.domain.refactor;

import com.eastwood.design.metrics.domain.RequestMetrics;
import com.eastwood.design.metrics.domain.RequestStat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Aggregator
 * @Description: Aggregator
 * @Author: yyh
 * @Date: 2023/3/28 17:40
 */
public class Aggregator {

    public Map<String, RequestStat> aggregate(Map<String, List<RequestMetrics>> requestInfos, long durationInMillis) {
        Map<String, RequestStat> requestStatMap = new HashMap<>();
        requestInfos.forEach((key, requestMetrics) -> {
            RequestStat requestStat = doAggregator(requestMetrics, durationInMillis);
            requestStatMap.put(key, requestStat);
        });
        return requestStatMap;
    }

    private RequestStat doAggregator(List<RequestMetrics> requestMetrics, long durationInMillis) {
        List<Double> respTimes = new ArrayList<>();
        for (RequestMetrics requestInfo : requestMetrics) {
            double respTime = requestInfo.getResponseTime();
            respTimes.add(respTime);
        }

        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(max(respTimes));
        requestStat.setMinResponseTime(min(respTimes));
        requestStat.setAvgResponseTime(avg(respTimes));
        requestStat.setP999ResponseTime(percentile999(respTimes));
        requestStat.setP99ResponseTime(percentile99(respTimes));
        requestStat.setCount(respTimes.size());
        requestStat.setTps((long) tps(respTimes.size(), durationInMillis/1000));
        return requestStat;
    }

    // 以下的函数的代码实现均省略...
    private double max(List<Double> dataset) { return 0d;}
    private double min(List<Double> dataset) { return 0d;}
    private double avg(List<Double> dataset) { return 0d;}
    private double tps(int count, double duration) { return 0d;}
    private double percentile999(List<Double> dataset) { return 0d;}
    private double percentile99(List<Double> dataset) { return 0d;}
    private double percentile(List<Double> dataset, double ratio) { return 0d;}
}
