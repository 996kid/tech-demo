package leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class ContainsNearbyDuplicate {

    public static void main(String[] args) {
        
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
            if (map.containsKey(i)) {

            } else {
            }
        }
        return true;
    }
}
