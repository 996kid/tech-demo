package com.eastwood.design.metrics.domain.refactor.viewer;

import com.eastwood.design.metrics.domain.RequestStat;

import java.util.Map;

/**
 * @ClassName: Viewer
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/13 19:48
 */
public interface Viewer {

    void output(Map<String, RequestStat> requestStatMap, long startTimeInMillis, long endTimeInMills);
}
