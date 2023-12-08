package leetcode.array;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description SearchInsert
 * @date 2023/12/8
 */

public class SearchInsert {

    public static void main(String[] args) {

    }

    public static int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }
}
