package leetcode.array;

import java.util.Arrays;

/**
 * @author 996kid@gmail.com
 * @version 1.0     
 * @description Merge 合并两个有序数组
 * @date 2023/12/8
 */

public class Merge {

    public static void main(String[] args) {

    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] tmp = Arrays.copyOfRange(nums1, 0, m);
        for (int i = 0; i < m + n; i++) {
            if (tmp[i] <= nums2[i]) {

            }
        }
    }
}
