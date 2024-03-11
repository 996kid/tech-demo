package leetcode.array;

import java.util.Arrays;

/**
 * @author 996kid@gmail.com
 * @date 2024/3/11
 */
public class PrintArray {

    public static void print(int nums[]) {
        Arrays.stream(nums).forEach(System.out::println);
    }
}
