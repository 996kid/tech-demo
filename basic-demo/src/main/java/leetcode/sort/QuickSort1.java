package leetcode.sort;

/**
 * 快速排序
 * 1. 从数列中挑出一个元素，称为 “基准”（pivot）；
 * 2. 重新排序数列，所有元素比基准值小的摆放在基准前面，
 *    所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 *    在这个分割之后，该基准是它的最后位置。这个称为分割（partition）操作；
 * 3. 递归地（recursively）把小于基准值元素的子数列和
 *    大于基准值元素的子数列排序。
 *
 * @Description QuickSort1
 * @Date 2020/9/4 17:09
 */

public class QuickSort1 {

    public static void main(String[] args) {
        int[] array = {1,2,31,4,54,9,12};
        quickSort(array, 0, array.length - 1);
        print(array);
    }

    private static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            // 选出一个基准值
            int pivot = partition(array, left, right);
            // 对基准值左边的元素进行快速排序
            quickSort(array, left, pivot - 1);
            // 对基准值右边的元素进行快速排序
            quickSort(array, pivot + 1, right);
        }
    }

    private static int partition(int[] array, int left, int right) {
        // 选出一个基准值
        int pivot = array[left];
        // 从基准值的下一个元素开始
        int j = left + 1;
        // 从基准值的下一个元素开始遍历
        for (int i = j; i <= right; i++) {
            // 如果当前元素小于基准值
            if (array[i] < pivot) {
                // 交换当前元素和基准值的下一个元素
                swap(array, i, j);
                // 基准值的下一个元素的下标加1
                j++;
            }
        }
        // 交换基准值和基准值的下一个元素
        swap(array, left, j - 1);
        // 返回基准值的下标
        return j - 1;
    }

    private static void swap(int[] array, int i, int j) {
        // 交换两个元素
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
