package com.eastwood;

/**
 * @author yyh
 * @Description MutilThreadTest
 * @Date 2020/9/1 15:50
 */
public class MutilThreadTest {

    static class Test {
        long count = 0;
        void add10K() {
            int idx = 0;
            while(idx++ < 10000) {
                count += 1;
            }
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        new Thread(() -> {
            test.add10K();
        });
    }
}
