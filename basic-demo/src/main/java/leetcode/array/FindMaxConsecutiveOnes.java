package leetcode.array;

import java.util.TreeSet;

/**
 * @author 996kid@gmail.com
 * @date 2024/2/4
 */
public class FindMaxConsecutiveOnes {

    /**
     * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,1,0,1,1,1]
     * 输出：3
     * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
     * 示例 2:
     *
     * 输入：nums = [1,0,1,1,0,1]
     * 输出：2
     *
     *
     * 提示：
     *
     * 1 <= nums.length <= 105
     * nums[i] 不是 0 就是 1.
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {1,0,1,1,0,1};
        System.out.println(findMaxConsecutiveOnes1(nums));
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        int maxCount = 0;
        treeSet.add(maxCount);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                maxCount++;
                if ((i < nums.length - 1 && nums[i + 1] == 0)
                        || i == nums.length - 1) {
                    treeSet.add(maxCount);
                    maxCount = 0;
                }
            }
        }
        return treeSet.last();
    }

    public static int findMaxConsecutiveOnes1(int[] nums) {
        int maxCount = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
                if ((i < nums.length - 1 && nums[i + 1] == 0)
                        || i == nums.length - 1) {
                    if (count > maxCount) {
                        maxCount = count;
                    }
                    count = 0;
                }
            }
        }
        return maxCount;
    }

    class Solution {
        public int findMaxConsecutiveOnes(int[] nums) {
            int maxCount = 0, count = 0;
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] == 1) {
                    count++;
                } else {
                    maxCount = Math.max(maxCount, count);
                    count = 0;
                }
            }
            maxCount = Math.max(maxCount, count);
            return maxCount;
        }
    }

}
