package com.eastwood.design.pattern.responsibilitychain.pipeline;

import java.util.function.Consumer;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description FinalGetConsumer
 * @date 2023/9/1
 */

public enum FinalGetConsumer implements Consumer<Me> {
    INSTANCE;

    @Override
    public void accept(Me me) {
        System.out.println("final get a peaceful me...");
        me.setStatus("peaceful");
    }
}
