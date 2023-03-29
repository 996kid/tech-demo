package com.eastwood.design.metrics.domain.collector;

import com.eastwood.design.metrics.domain.storage.MetricsStorage;
import com.eastwood.design.metrics.domain.RequestMetrics;

/**
 * @ClassName: MetricsCollector
 * @Description: Metrics Collector
 *               设计开发一个小的框架，能够获取接口调用的各种统计信息，
 *               比如，响应时间的最大值（max）、最小值（min）、平均值（avg）、百分位值（percentile）、接口调用次数（count）、频率（tps）
 *               等，并且支持将统计结果以各种显示格式（比如：JSON格式、网页格式、自定义显示格式等）输出到各种终端（Console命令行、HTTP网页、
 *               Email、日志文件、自定义输出终端等），以方便查看
 * @Author: yyh
 * @Date: 2023/3/28 14:55
 */
public class MetricsCollector {

    private final MetricsStorage metricsStorage;

    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    public void recordRequest(RequestMetrics requestMetrics) {
        metricsStorage.saveMetrics(requestMetrics);
    }
}
