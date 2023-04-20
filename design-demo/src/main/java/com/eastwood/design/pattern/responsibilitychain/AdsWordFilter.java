package com.eastwood.design.pattern.responsibilitychain;

/**
 * @ClassName: AdsWordFilter
 * @Description: AdsWordFilter
 * @Author: yyh
 * @Date: 2023/4/20 9:58
 */
public class AdsWordFilter implements SensitiveWordFilter {

    @Override
    public boolean doFilter(Content content) {
        return false;
    }
}
