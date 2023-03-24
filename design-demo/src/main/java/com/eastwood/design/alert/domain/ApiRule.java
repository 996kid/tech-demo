package com.eastwood.design.alert.domain;

/**
 * @ClassName: ApiRule
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/3/22 21:09
 */
public class ApiRule {

    public long getMaxTps() {
        return 200;
    }

    public long getMaxErrorCount() {
        return 200;
    }
}
