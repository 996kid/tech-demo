package com.eastwood.design.metrics.domain;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName: Aggregator
 * @Description: Aggregator
 * @Author: yyh
 * @Date: 2023/3/28 17:40
 */
public class Aggregator {

    public static RequestStat aggregate(List<RequestMetrics> requestInfos, long durationInMillis) {
        // 最大响应时间
        double maxRespTime = Double.MIN_VALUE;
        // 最小响应时间
        double minRespTime = Double.MAX_VALUE;
        // 平均响应时间
        double avgRespTime = -1;
        // p999
        double p999RespTime = -1;
        double p99RespTime = -1;
        // 总响应时间
        double sumRespTime = 0;
        long count = 0;
        for (RequestMetrics requestInfo : requestInfos) {
            ++count;
            double respTime = requestInfo.getResponseTime();
            if (maxRespTime < respTime) {
                maxRespTime = respTime;
            }
            if (minRespTime > respTime) {
                minRespTime = respTime;
            }
            sumRespTime += respTime;
        }
        if (count != 0) {
            avgRespTime = sumRespTime / count;
        }
        long tps = count / durationInMillis * 1000;
        Collections.sort(requestInfos, (o1, o2) -> {
            double diff = o1.getResponseTime() - o2.getResponseTime();
            if (diff < 0.0) {
                return -1;
            } else if (diff > 0.0) {
                return 1;
            } else {
                return 0;
            }
        });
        int idx999 = (int)(count * 0.999);
        int idx99 = (int)(count * 0.99);
        if (count != 0) {
            p999RespTime = requestInfos.get(idx999).getResponseTime();
            p99RespTime = requestInfos.get(idx99).getResponseTime();
        }
        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(maxRespTime);
        requestStat.setMinResponseTime(minRespTime);
        requestStat.setAvgResponseTime(avgRespTime);
        requestStat.setP999ResponseTime(p999RespTime);
        requestStat.setP99ResponseTime(p99RespTime);
        requestStat.setCount(count);
        requestStat.setTps(tps);
        return requestStat;
    }
}
