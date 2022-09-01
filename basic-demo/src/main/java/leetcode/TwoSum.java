package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 996kid@gmail.com
 * @Description TwoSum
 * @Date 2022/7/16 18:47
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] result = twoSum(new int[] {2,7,11,15}, 9);
        for (int r : result) {
            System.out.println(r);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int t = nums[i];
            if (map.containsKey(target - t)) {
               return new int[] {i, map.get(target - t)};
            }
            map.put(t, i);
        }
        return new int[]{};
    }
}
