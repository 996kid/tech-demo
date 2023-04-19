package com.eastwood.design.pattern.observer;

/**
 * @ClassName: Observable
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/19 11:50
 */
public interface Observable {

    void notifyObservers(Object args);
}
