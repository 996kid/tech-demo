package sort;

/**
 * @author 996kid@gmail.com
 * @Description ChooseSort 选择排序
 * @Date 2022/4/10 22:36
 */
public class ChooseSort {

    public static void main(String[] args) {
        int[] array = {1,2,31,4,54,9,12};
        chooseSort(array);
        print(array);
    }

    /**
     * 1. 算法步骤
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
     *
     * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     *
     * 重复第二步，直到所有元素均排序完毕。
     * @param nums
     */
    public static void chooseSort(int[] nums) {
        int min = 0;
        int minIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            min = nums[i];
            minIndex = i;
            // 找到最小的
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < min) {
                    min = nums[j];
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void print(int[] array) {
        for (int i : array) {
            System.out.print(i + ",");
        }
    }
}
