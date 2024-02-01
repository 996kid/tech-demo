package com.eastwood.design.pattern.responsibilitychain.pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description SleepPileline   职责链模式   流水线处理业务 每个处理者是枚举实现的单例类
 * @date 2023/9/1
 */

public class SleepPileline {

    private final Consumer<Me> firstConsumer;

    public SleepPileline() {
        // 返回了一个Consumer
        /**
         * (T t) -> {
         * accept(t);
         * after.accept(t);
         * }
         *
         * 这样的一个方法调用序列
         * TakeOffConsumer.accept() --> CloseEyesConsumer.accept() --> FinalGetConsumer.accept() --> last
         */
        firstConsumer = SleepPileline.start(TakeOffConsumer.INSTANCE)
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
        new SleepPileline().firstConsumer.accept(new Me("sleepy"));

        List<Integer> list = new ArrayList<>();
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
        
        for (Integer i : list) {
            new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) {
                    System.out.println(integer);
                }
            }.accept(i);
        }
    }
}
