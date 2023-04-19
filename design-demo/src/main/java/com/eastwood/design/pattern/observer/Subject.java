package com.eastwood.design.pattern.observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName: Subject
 * @Description: Subject-Observers
 * @Author: yyh
 * @Date: 2023/4/19 11:41
 */
public class Subject implements Observable{

    // 读写可并行
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Object args) {
        observers.stream().forEach(i -> i.update(this, args));
    }


}
