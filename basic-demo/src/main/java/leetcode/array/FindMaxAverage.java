package leetcode.array;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 996kid@gmail.com
 * @date 2024/3/4
 */
public class FindMaxAverage {

    /**
     * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
     * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
     * 任何误差小于 10-5 的答案都将被视为正确答案。
     * 
     * nums = [1,12,-5,-6,50,3], k = 4     12.75
     *
     * [5] 1
     *
     * [0,1,1,3,3]  4    2     1.25
     *
     * [-1] 1
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] a = {-1};
        System.out.println(findMaxAverage(a, 1));
    }

    /**
     * 滑动窗口
     * @param nums
     * @param k
     * @return
     */
    public static double findMaxAverage(int[] nums, int k) {
        // 滑动窗口中数字之和  每滑动一位则加上新增的 减去移出的数字
        int sum = 0;
        BigDecimal avg = new BigDecimal(Integer.MIN_VALUE);

        List<Integer> window = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (window.size() < k) {
                window.add(nums[i]);
                sum += nums[i];
            } else {
                // 窗口已达到最大值
                BigDecimal t = new BigDecimal(sum).divide(new BigDecimal(k), 5, RoundingMode.HALF_UP);
                if (t.compareTo(avg) > 0) {
                    avg = t;
                }

                sum += nums[i];
                sum -= window.get(0);

                window.remove(0);
                window.add(nums[i]);
            }
        }

        BigDecimal t = new BigDecimal(sum).divide(new BigDecimal(k), 5, RoundingMode.HALF_UP);
        if (t.compareTo(avg) > 0) {
            avg = t;
        }
        return avg.doubleValue();
    }

    class Solution {
        public double findMaxAverage(int[] nums, int k) {
            int sum = 0;
            int n = nums.length;
            for (int i = 0; i < k; i++) {
                sum += nums[i];
            }
            int maxSum = sum;
            for (int i = k; i < n; i++) {
                sum = sum - nums[i - k] + nums[i];
                maxSum = Math.max(maxSum, sum);
            }
            return 1.0 * maxSum / k;
        }
    }

}
