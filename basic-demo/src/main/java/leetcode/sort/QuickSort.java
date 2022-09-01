package leetcode.sort;

/**
 * @author 996kid@gmail.com
 * @Description QuickSort
 * @Date 2022/7/23 22:50
 */
public class QuickSort {

    private static int[] quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return nums;
        }
        int p = partition(nums);
        quickSort(nums, left, p - 1);
        quickSort(nums, p + 1, right);
        return nums;
    }

    // 取数组最后一位为分区数,  分区后比分区数小的在其左边
    // 大的在其右边, 返回分区数的索引
    private static int partition(int[] nums) {
        // 分区数
        int p = nums[nums.length - 1];
        


        return 0;
    }
}
