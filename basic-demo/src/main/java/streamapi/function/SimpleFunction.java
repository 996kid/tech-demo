package streamapi.function;

/**
 * @ClassName: SimpleFunction
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/14 13:59
 */
@FunctionalInterface
public interface SimpleFunction<T extends Object> {

    void print(T t);
}
