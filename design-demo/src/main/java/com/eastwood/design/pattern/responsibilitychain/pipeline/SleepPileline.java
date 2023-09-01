package com.eastwood.design.pattern.responsibilitychain.pipeline;

import java.util.function.Consumer;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description SleepPileline   职责链模式   流水线处理业务 每个处理者是枚举实现的单例类
 * @date 2023/9/1
 */

public class SleepPileline {

    private final Consumer<Me> consumers;

    public SleepPileline() {
        consumers = SleepPileline.start(TakeOffConsumer.INSTANCE)
                .andThen(CloseEyesConsumer.INSTANCE)
                .andThen(FinalGetConsumer.INSTANCE)
                .andThen(last())
        ;
    }

    private static Consumer<Me> start(Consumer<Me> consumer) {
        return consumer;
    }

    private static Consumer<Me> last() {
        return me1 -> System.out.printf("Zzz%s...", me1.getStatus());
    }





    public static void main(String[] args) {
        new SleepPileline().consumers.accept(new Me("sleepy"));
    }
}
