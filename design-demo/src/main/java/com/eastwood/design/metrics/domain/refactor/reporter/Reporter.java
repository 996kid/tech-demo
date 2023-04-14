package com.eastwood.design.metrics.domain.refactor.reporter;

/**
 * @ClassName: Reporter
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/13 15:54
 */
public interface Reporter {

    void startRepeatedReport(long periodInSeconds, long durationInSeconds);
}
