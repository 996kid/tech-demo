package leetcode.array;

import java.util.Arrays;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description PlusOne
 * @date 2023/12/8
 */

public class PlusOne {

    public static void main(String[] args) {
        int[] digits = {1,2,3};
        int[] results = new int[digits.length + 1];
        results[0] = 0;
        for (int i = 0; i < digits.length; i++) {
            results[i + 1] = digits[i];
        }
        // 最大情况多一位数字
        plusOneInRecursion(results, 1);
        if (results[0] == 0) {
            for (int i = 0; i < results.length; i++) {
                results[i + 1] = results[i];
            }
        }
    }

    private static void plusOneInRecursion(int[] array, int step) {
        if (array[array.length - step] < 9) {
            array[array.length - step]++;
        } else {
            // 最后一位是9 向前进1
            array[array.length - step] = 0;
            plusOneInRecursion(array, step + 1);
        }
    }
}
