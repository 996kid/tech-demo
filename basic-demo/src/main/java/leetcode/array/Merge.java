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
        int[] nums1 = {1,0};
        int[] nums2 = {2};
        merge(nums1, 1, nums2, 1);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        int[] tmp = Arrays.copyOfRange(nums1, 0, m);
        int i = 0;
        int j = 0;
        int p = 0;
        while (i <= m - 1 && j <= n - 1) {
            if (tmp[i] <= nums2[j]) {
                nums1[p++] = tmp[i];
                if (i <= tmp.length - 1) {
                    i++;
                }
            } else {
                nums1[p++] = nums2[j];
                if (j <= n - 1) {
                    j++;
                }
            }
        }

        if (i > m - 1 && j <= n - 1) {
            for (; j < n; j++ ) {
                nums1[p++] = nums2[j];
            }
        }
        if (i <= m - 1 && j > n - 1) {
            for (; i < m; i++ ) {
                nums1[p++] = tmp[i];
            }
        }
    }
}
