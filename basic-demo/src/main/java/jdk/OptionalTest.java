package jdk;

import java.util.Optional;

/**
 * @author yyh
 * @Description OptionalTest
 * @Date 2020/8/18 16:22
 */
public class OptionalTest {

    public static void main(String[] args) {
        Object object = new Object();
        Optional.ofNullable(object).orElse("");
    }
}
