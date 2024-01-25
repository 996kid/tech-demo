package leetcode.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 996kid@gmail.com
 * @date 2024/1/25
 */
public class MissingNumber {

    /**
     * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     * 输入：nums = [3,0,1]
     * 输出：2
     * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     *
     * 输入：nums = [0,1]
     * 输出：2
     * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
     *
     * 提示：
     *
     * n == nums.length
     * 1 <= n <= 104
     * 0 <= nums[i] <= n
     * nums 中的所有数字都 独一无二
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {3,0,1};
        System.out.println(missingNumber2(nums));
    }

    /**
     * list
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        for (int i = 0; i <= n; i ++) {
            if (!list.contains(i)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * set
     * @param nums
     * @return
     */
    public static int missingNumber1(int[] nums) {
        int n = nums.length;
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        for (int i = 0; i <= n; i ++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 0;
    }


    /**
     * 位运算： 异或运算满足交换律和结合律
     *
     * 数组 nums[] 中有 n 个数，在这 n 个数的后面添加从 0 到 n 的每个整数，则添加了 n+1 个整数，共有 2n+1 个整数。
     *
     * 在 2n+1 个整数中，丢失的数字只在后面 n+1 个整数中出现一次，其余的数字在前面 n 个整数中（即数组中）和后面 n+1 个整数中各出现一次，即其余的数字都出现了两次。
     *
     * 根据出现的次数的奇偶性，可以使用按位异或运算得到丢失的数字。按位异或运算 ⊕\oplus⊕ 满足交换律和结合律，且对任意整数 xxx 都满足 x⊕x=0 和 x⊕0=x。
     *
     * 由于上述 2n+1 个整数中，丢失的数字出现了一次，其余的数字都出现了两次，因此对上述 2n+1个整数进行按位异或运算，结果即为丢失的数字。
     *
     */

    public static int missingNumber2(int[] nums) {
        int r = 0;
        for (int i = 0; i <= nums.length; i++) {
            if (i < nums.length) {
                r ^= i;
                r ^= nums[i];
            } else {
                r ^= i;
            }
        }
        return r;
    }
}
