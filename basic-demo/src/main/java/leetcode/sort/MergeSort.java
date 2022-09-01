package leetcode.sort;

import java.util.Arrays;

/** 归并排序
 * 分治 和 递归
 * 递推公式：
 * merge_sort(p…r) = merge(merge_sort(p…q), merge_sort(q+1…r))
 *
 * 终止条件：
 * p >= r 不用再继续分解
 * @author 996kid@gmail.com
 * @Description MergeSort
 * @Date 2022/7/21 14:49
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] nums = new int[] {2, 3, 9, 4, 5};
//        int[] nums = new int[] {2, 3};
        int[] sortedNums = mergeSort(nums);
        System.out.println(Arrays.toString(sortedNums));
    }

    private static int[] mergeSort(int[] nums) {
        // 复制原来的数组
        int[] arr = Arrays.copyOf(nums, nums.length);
        if (arr.length < 2) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        int[] subNums1 = mergeSort(left);
        int[] subNums2 = mergeSort(right);
        return merge(arr, subNums1, subNums2);
    }

    private static int[] merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        //用两个游标 i 和 j，分别指向 A[p…q] 和 A[q+1…r] 的第一个元素。
        //比较这两个元素 A[i] 和 A[j]，如果 A[i]<=A[j]，
        //把 A[i] 放入到临时数组 tmp，并且 i 后移一位，否则将 A[j] 放入到数组 tmp，j 后移一位
        // 临时数组 会被回收
        int[] tmp = new int[arr.length];
        while (i <= left.length - 1 && j <= right.length - 1) {
            if (left[i] <= right[j]) {
                tmp[k++] = left[i++];
            } else {
                tmp[k++] = right[j++];
            }
        }
        // 判断哪个数组中还有元素
        // 将剩下的元素复制到临时数组
        if (i > left.length - 1) {
            while (j <= right.length - 1) {
                tmp[k++] = right[j++];
            }
        } else if (j > right.length - 1) {
            while (i <= left.length - 1) {
                tmp[k++] = left[i++];
            }
        }
        for (int a = 0; a < arr.length; a++) {
            arr[a] = tmp[a];
        }
        return arr;
    }

}
