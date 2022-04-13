package leetcode;

/**
 * @author 996kid@gmail.com
 * @Description RemoveDuplicate
 * @Date 2022/4/5 13:58
 */
public class RemoveDuplicate {

    public static void main(String[] args) {
        int[] arr = {0, 0, 0, 1, 2, 3};
//        moveLeft(arr, 0);
        int length = removeDuplicates2(arr);
        System.out.println(length);
        for (int i : arr) {
            System.out.print(i + ",");
        }
    }

    //双指针解决
    public static int removeDuplicates1(int[] A) {
        //边界条件判断
        if (A == null || A.length == 0)
            return 0;
        int left = 0;
        for (int right = 1; right < A.length; right++)
            //如果左指针和右指针指向的值一样，说明有重复的，
            //这个时候，左指针不动，右指针继续往右移。如果他俩
            //指向的值不一样就把右指针指向的值往前挪
            if (A[left] != A[right])
                A[++left] = A[right];
        return ++left;
    }

    /**删除排序数组中的重复项
     * @param nums
     * @return
     */
    public static int removeDuplicates2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            while (nums[i+1] == nums[i]) {
                moveLeft(nums, i + 1);
            }
        }
        return nums.length;
    }


    /** 向左移动一位
     * @param nums
     * @param i
     */
    private static void moveLeft(int[] nums, int i) {
        for (int j = i; j < nums.length - 1; j++) {
            nums[j] = nums[j + 1];
        }
    }
}
