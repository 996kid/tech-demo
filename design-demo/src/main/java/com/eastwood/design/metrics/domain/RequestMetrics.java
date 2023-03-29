package com.eastwood.design.metrics.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: RequestMetrics
 * @Description: Request Metrics
 * @Author: yyh
 * @Date: 2023/3/28 16:18
 */
@Setter
@Getter
public class RequestMetrics {

    private String api;

    private long timestamp;

    private double responseTime;
}
