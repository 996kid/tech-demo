package com.eastwood.design.pattern.responsibilitychain;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: FilterChain
 * @Description: FilterChain  职责链模式
 *               将请求的发送和接收解耦，让多个接收对象都有机会处理这个请求。
 *               将这些接收对象串成一条链，并沿着这条链传递这个请求，直到链上的某个接收对象能够处理它为止
 *
 *               敏感词校验场景：
 *                   发送内容只要涉黄, 或者有广告就会在职责链上某个节点被处理
 *               适用场景：
 *                   各节点处理逻辑较为复杂
 *                   对修改关闭，对扩展开放
 * @Author: yyh
 * @Date: 2023/4/20 9:39
 */
public class FilterChain {

    private final List<SensitiveWordFilter> filters = new ArrayList<>();

    public void addFilter(SensitiveWordFilter filter) {
        filters.add(filter);
    }


    public void handle(Content content) {
        if (null == content) {
            throw new IllegalArgumentException("Illegal arg");
        }
        for (SensitiveWordFilter filter : filters) {
            if (filter.doFilter(content)) break;
        }
    }
}
