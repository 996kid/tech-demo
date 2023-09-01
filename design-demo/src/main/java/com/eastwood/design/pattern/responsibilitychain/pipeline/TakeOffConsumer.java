package com.eastwood.design.pattern.responsibilitychain.pipeline;

import java.util.function.Consumer;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description TakeOffConsumer
 * @date 2023/9/1
 */

public enum TakeOffConsumer implements Consumer<Me> {

    // 枚举实现的单例
    INSTANCE;

    @Override
    public void accept(Me me) {
        System.out.println("naked me...");
    }
}
