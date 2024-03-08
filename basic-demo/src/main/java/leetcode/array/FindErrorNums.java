package leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindErrorNums {


    /**
     * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导
     * 致集合里面某一个数字复制了成了集合里面的另外一个数字的值，
     * 导致集合丢失了一个数字 并且 有一个数字重复 。
     * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
     * 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
     * 示例 1：
     *
     * 输入：nums = [1,2,2,4] [1,2,3,4]           [1,3,3,4]  [1,2,3,4,2,6]
     * 输出：[2,3]
     * 示例 2：
     *
     * 输入：nums = [1,1]                         [1,2]
     * 输出：[1,2]
     *
     * 提示：
     *
     * 2 <= nums.length <= 10^4
     * 1 <= nums[i] <= 10^4
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {1,2,2,4};
        Arrays.stream(findErrorNums1(nums)).forEach(System.out::println);
    }

    /**
     * 尝试用异或方式解决
     * @param nums
     * @return
     */
    public static int[] findErrorNums(int[] nums) {
        int[] newArr = new int[nums.length * 2];
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            newArr[i] = nums[i];
            newArr[i + nums.length] = i + 1;
            s.add(nums[i]);
        }
        for (int i = 0; i < nums.length * 2; i++) {
        
        }
        return null;
    }

    public static int[] findErrorNums1(int[] nums) {
        /**
         *
         */
        int[] r = new int[2];
        Set<Integer> s = new HashSet<>();
        for (int i : nums) {
            if (!s.add(i)) {
                r[0] = i;
            }
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!s.contains(i)) {
                r[1] = i;
            }
        }
        return r;
    }


        /**
         * 位运算
         * @param nums
         * @return
         */
        public int[] findErrorNums2(int[] nums) {
            int n = nums.length;
            int xor = 0;
            for (int num : nums) {
                xor ^= num;
            }
            for (int i = 1; i <= n; i++) {
                xor ^= i;
            }
            int lowbit = xor & (-xor);
            int num1 = 0, num2 = 0;
            for (int num : nums) {
                if ((num & lowbit) == 0) {
                    num1 ^= num;
                } else {
                    num2 ^= num;
                }
            }
            for (int i = 1; i <= n; i++) {
                if ((i & lowbit) == 0) {
                    num1 ^= i;
                } else {
                    num2 ^= i;
                }
            }
            for (int num : nums) {
                if (num == num1) {
                    return new int[]{num1, num2};
                }
            }
            return new int[]{num2, num1};
        }

    /**
     * 寻找重复的数  数组排序后相邻数字相等的即为重复的数
     * @param nums
     * @return
     */
    public int[] findErrorNums3(int[] nums) {
            int[] errorNums = new int[2];
            int n = nums.length;
            Arrays.sort(nums);
            int prev = 0;
            for (int i = 0; i < n; i++) {
                int curr = nums[i];
                if (curr == prev) {
                    errorNums[0] = prev;
                } else if (curr - prev > 1) {
                    errorNums[1] = prev + 1;
                }
                prev = curr;
            }
            if (nums[n - 1] != n) {
                errorNums[1] = n;
            }
            return errorNums;
        }

}
