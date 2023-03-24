package com.eastwood.design.alert.domain;

import lombok.Getter;

/**
 * @ClassName: ApiInfo
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/3/22 21:42
 */
@Getter
public class ApiInfo {

    String api;

    long requestCount;

    long errorCount;

    long durationOfSeconds;

    public long getTps() {
        return requestCount/durationOfSeconds;
    }
}
