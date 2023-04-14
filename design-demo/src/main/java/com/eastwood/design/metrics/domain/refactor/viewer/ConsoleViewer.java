package com.eastwood.design.metrics.domain.refactor.viewer;

import com.eastwood.design.metrics.domain.RequestStat;
import com.google.gson.Gson;

import java.util.Map;

/**
 * @ClassName: ConsoleViewer
 * @Description: ConsoleViewer
 * @Author: yyh
 * @Date: 2023/4/13 19:50
 */
public class ConsoleViewer implements Viewer {
    @Override
    public void output(Map<String, RequestStat> requestStatMap, long startTimeInMillis, long endTimeInMills) {
        // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）；
        System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMills + "]");
        Gson gson = new Gson();
        System.out.println(gson.toJson(requestStatMap));
    }
}
