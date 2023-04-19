package com.eastwood.design.pattern.observer;

/**
 * @ClassName: Observer
 * @Description: Observer
 * @Author: yyh
 * @Date: 2023/4/19 11:44
 */
public interface Observer {

    void update(Observable o, Object arg);
}
