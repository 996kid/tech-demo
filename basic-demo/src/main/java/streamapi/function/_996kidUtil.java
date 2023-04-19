package streamapi.function;

/**
 * @ClassName: _996kidUtil
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/14 13:32
 */
public class _996kidUtil {

    public static ThrowExceptionFunction isTrue(boolean b) {
        return message -> {
            if (b) throw new RuntimeException(message);
        };
    }

    public static BranchHandle isTrueOrFalse(boolean b) {
        return (trueHandle, falseHandle) -> {
            if (b) trueHandle.run();
            else falseHandle.run();
        };
    }

    public static PresentOrElseHandler<?> isBlankOrNoBlank(String str){
        return (consumer, runnable) -> {
            if (str == null || str.length() == 0){
                runnable.run();
            } else {
                consumer.accept(str);
            }
        };
    }

    public static void testFunction(SimpleFunction simpleFunction) {
        simpleFunction.print("hello");
    }


    public static void main(String[] args) {

        // 模板方法模式
        _996kidUtil.testFunction(new SimpleFunction() {
            @Override
            public void print(Object o) {
                System.out.println(o);
            }
        });

        _996kidUtil.testFunction(System.out::println);




        _996kidUtil.isTrue(true).throwMessage("业务异常");

        _996kidUtil.isTrueOrFalse(true).trueOrFalseHandle(() -> {

        }, () -> {

        });

        _996kidUtil.isBlankOrNoBlank("hello").presentOrElseHandle(o -> {

        }, () -> {});
    }
}
