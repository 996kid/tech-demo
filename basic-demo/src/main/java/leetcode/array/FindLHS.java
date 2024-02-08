package leetcode.array;

import java.util.*;

/**
 * @author 996kid@gmail.com
 * @date 2024/2/7
 */
public class FindLHS {

    /**
     * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
     * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
     * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
     * 示例 1：
     *
     * 输入：nums = [1,3,2,2,5,2,3,7]
     * 输出：5
     * 解释：最长的和谐子序列是 [3,2,2,2,3]
     *
     * 1 <= nums.length <= 2 * 104
     * -109 <= nums[i] <= 109
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {1,3,2,2,5,2,3,7};
        System.out.println(findLHS(nums));
    }


    /**
     * 我们可以枚举数组中的每一个元素，对于当前枚举的元素 x，它可以和 x+1组成和谐子序列。
     * 我们只需要在数组中找出等于 x 或 x+1 的元素个数，就可以得到以 x 为最小值的和谐子序列的长度。
     *
     * 实际处理时，我们可以将数组按照从小到大进行排序，我们只需要依次找到相邻两个连续相同元素的子序列，
     * 检查该这两个子序列的元素的之差是否为 1。
     * 利用类似与滑动窗口的做法，begin 指向第一个连续相同元素的子序列的第一个元素，
     * end 指向相邻的第二个连续相同元素的子序列的末尾元素，如果满足二者的元素之差为 1，则当前的和谐子序列的长度即为两个子序列的长度之和，
     * 等于 end−begin+1。
     *
     * @param nums
     * @return
     */
    public static int findLHS1(int[] nums) {
        // {1,2,2,2,3,3,5,7}
        Arrays.sort(nums);
        int begin = 0;
        int res = 0;
        // 移动结尾指针
        for (int end = 0; end < nums.length; end++) {
            // 如果遍历的数和开头的数差值大于1就移动开始指针
            while (nums[end] - nums[begin] > 1) {
                begin++;
            }
            if (nums[end] - nums[begin] == 1) {
                res = Math.max(res, end - begin + 1);
            }
        }
        return res;
    }





    public static int findLHS(int[] nums) {
        List<Integer> window = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            // 已经包含在和谐数组中 直接加入
            if (window.contains(i)) {
                window.add(i);
            } else {
                // 不包含在数组中， 判断和其中数字的绝对值
                // 如果大于1 且又不在数组中 判断set的size 如果等于1可以加入 大于1 则该数字为第三个数
                boolean f = true;
                for (int j : set) {
                    if (Math.abs(j - i) > 1) {
                        if (set.size() > 1) {
                            f = false;
                            break;
                        }
                    }

                }
                if (f) {
                    window.add(i);
                    set.add(i);
                }
            }
        }

        return window.size();
    }
}
