package leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 996kid@gmail.com
 * @date 2024/3/21
 */
public class FindShortestSubArray {

    /**
     * 给定一个非空且只包含非负数的整数数组 nums，数组的 度 的定义是指数组里任一元素出现频数的最大值。
     * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
     *
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,2,3,1]
     * 输出：2
     * 解释：
     * 输入数组的度是 2 ，因为元素 1 和 2 的出现频数最大，均为 2 。
     * 连续子数组里面拥有相同度的有如下所示：
     * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
     * 最短连续子数组 [2, 2] 的长度为 2 ，所以返回 2 。
     * 示例 2：
     *
     * 输入：nums = [1,2,2,3,1,4,2]
     * 输出：6
     * 解释：
     * 数组的度是 3 ，因为元素 2 重复出现 3 次。
     * 所以 [2,2,3,1,4,2] 是最短子数组，因此返回 6 。
     * @param args
     */
    public static void main(String[] args) {
        int[] a = {2,1};
        System.out.println(findShortestSubArray1(a));
    }

    public static int findShortestSubArray(int[] nums) {
        /**
         * 出现次数最多的数
         * 记录每个数出现次数
         * 每个数出现的开始位置和最后位置
         * 最短子数组长度即为最后位置 - 开始位置 + 1
         *
         */
        int max = 0;
        //{1,2,2,3,1}
        Map<Integer, Map<String, Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Map<String, Integer> m = map.get(nums[i]);
                max = Math.max(max, m.get("times") + 1);
                m.put("times", m.get("times") + 1);
                m.put("endIndex", i);
                map.put(nums[i], m);
            } else {
                Map<String, Integer> m = new HashMap<>();
                m.put("times", 1);
                max = Math.max(max, 1);
                m.put("startIndex", i);
                m.put("endIndex", i);
                map.put(nums[i], m);
            }
        }
        int min = nums.length;
        for (Map.Entry<Integer, Map<String, Integer>> entry : map.entrySet()) {
            if (entry.getValue().get("times") == max) {
                min = Math.min(min, entry.getValue().get("endIndex") - entry.getValue().get("startIndex") + 1);
            }
        }
        return min;
    }

    public static int findShortestSubArray1(int[] nums) {
        /**
         * 出现次数最多的数
         * 记录每个数出现次数
         * 每个数出现的开始位置和最后位置
         * 最短子数组长度即为最后位置 - 开始位置 + 1
         *
         */
        int max = 0;
        //{1,2,2,3,1}  0: times 1: beginIndex 2: endIndex
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int[] arr = map.get(nums[i]);
                max = Math.max(max, arr[0] + 1);
                arr[0]++;
                arr[2] = i;
                map.put(nums[i], arr);
            } else {
                int[] arr = new int[3];
                max = Math.max(max, 1);
                arr[0] = 1;
                arr[1] = arr[2] = i;
                map.put(nums[i], arr);
            }
        }
        int min = nums.length;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            if (entry.getValue()[0] == max) {
                min = Math.min(min, entry.getValue()[2] - entry.getValue()[1] + 1);
            }
        }
        return min;
    }
}
