package leetcode.array;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description RemoveElements
 * @date 2023/12/8
 */

public class RemoveElements {

    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        System.out.println(removeElement(nums, 2));
    }

    public static int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int size = 0;
        for (int i : nums) {
            if (i != val) {
                nums[size] = i;
                size++;
            }
        }
        return size;
    }
}
