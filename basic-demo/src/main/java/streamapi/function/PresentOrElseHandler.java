package streamapi.function;

import java.util.function.Consumer;

/**
 * @ClassName: PresentOrElseHandler
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/14 13:48
 */
@FunctionalInterface
public interface PresentOrElseHandler<T extends Object> {

    /**
     * 值不为空时执行消费操作
     * 值为空时执行其他的操作
     *
     * @param action 值不为空时，执行的消费操作
     * @param emptyAction 值为空时，执行的操作
     * @return void
     **/
    void presentOrElseHandle(Consumer<? super T> action, Runnable emptyAction);
}
