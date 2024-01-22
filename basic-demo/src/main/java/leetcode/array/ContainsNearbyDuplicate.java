package leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class ContainsNearbyDuplicate {

    public static void main(String[] args) {
        int[] nums = {1,0,1,1};
        System.out.println(containsNearbyDuplicate(nums, 1));
    }

    /**
     * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，
     * 满足 nums[i] == nums[j] 且 绝对值 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
     * @param nums
     * @param k
     * @return
     */
    
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++ ) {
            if (map.containsKey(nums[i])) {
                int iIndex = map.get(nums[i]);
                if (Math.abs(iIndex - i) <= k) {
                    return true;
                } else {
                    map.put(nums[i], i);
                }
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }

    /**
     * 滑动窗口
     * 考虑数组 nums 中的每个长度不超过 k+1的滑动窗口，同一个滑动窗口中的任意两个下标差的绝对值不超过 k。
     * 如果存在一个滑动窗口，其中有重复元素，则存在两个不同的下标 i 和 j 满足 nums[i]=nums[j]且 ∣i−j∣≤k。
     * 如果所有滑动窗口中都没有重复元素，则不存在符合要求的下标。因此，只要遍历每个滑动窗口，判断滑动窗口中是否有重复元素即可。
     *
     * 如果一个滑动窗口的结束下标是 i，则该滑动窗口的开始下标是 max(0,i−k)。可以使用哈希集合存储滑动窗口中的元素。从左到右遍历数组 nums，当遍历到下标 i 时，具体操作如下：
     *
     * 如果 i>k，则下标 i−k−1处的元素被移出滑动窗口，因此将 nums[i−k−1]从哈希集合中删除；
     *
     * 判断 nums[i] 是否在哈希集合中，如果在哈希集合中则在同一个滑动窗口中有重复元素，返回 true，如果不在哈希集合中则将其加入哈希集合。
     *
     * 当遍历结束时，如果所有滑动窗口中都没有重复元素，返回 false
     * @param nums
     * @param k
     * @return
     */
    public static boolean containsNearbyDuplicate1(int[] nums, int k) {
        return false;
    }
}
