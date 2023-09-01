package com.eastwood.design.pattern.responsibilitychain.pipeline;

import java.util.function.Consumer;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description CloseEyesConsumer
 * @date 2023/9/1
 */

public enum CloseEyesConsumer implements Consumer<Me> {
    INSTANCE;

    @Override
    public void accept(Me me) {
        System.out.println("close my eyes...");
    }
}
