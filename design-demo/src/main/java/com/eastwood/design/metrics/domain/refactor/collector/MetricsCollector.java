package com.eastwood.design.metrics.domain.refactor.collector;

import com.eastwood.design.metrics.domain.RequestMetrics;
import com.eastwood.design.metrics.domain.storage.MetricsStorage;
import com.eastwood.design.metrics.domain.storage.RedisMetricsStorage;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import io.micrometer.core.instrument.util.StringUtils;

import java.util.concurrent.Executors;

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

    private static final int THREAD_POOL_SIZE = 16;

    private EventBus eventBus;

    public MetricsCollector(MetricsStorage metricsStorage) {
        this(metricsStorage, THREAD_POOL_SIZE);
    }

    public MetricsCollector(MetricsStorage metricsStorage, int threadPoolSize) {
        this.metricsStorage = metricsStorage;
        this.eventBus = new AsyncEventBus(Executors.newFixedThreadPool(threadPoolSize));
        this.eventBus.register(new EventListener());
    }

    public void recordRequest(RequestMetrics requestMetrics) {
        if (requestMetrics == null || StringUtils.isBlank(requestMetrics.getApi())) {
            return;
        }
        // 异步保存请求指标
        eventBus.post(requestMetrics);
    }

    class EventListener {

        @Subscribe
        public void save(RequestMetrics requestMetrics) {
            System.out.println(Thread.currentThread().getId());
            metricsStorage.saveMetrics(requestMetrics);
        }
    }

    public static void main(String[] args) {
        MetricsCollector metricsCollector = new MetricsCollector(new RedisMetricsStorage());
        System.out.println(Thread.currentThread().getId());
        metricsCollector.recordRequest(new RequestMetrics().setApi("hello"));
    }
}
