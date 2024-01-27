package leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.ToIntFunction;

public class Intersection {


    /**
     * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
     *
     *
     * 示例 1：
     *
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     * 示例 2：
     *
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[9,4]
     * 解释：[4,9] 也是可通过的
     *
     * 提示：
     *
     * 1 <= nums1.length, nums2.length <= 1000
     * 0 <= nums1[i], nums2[i] <= 1000
     * @param args
     */
    public static void main(String[] args) {
        int[] s1 = {1,2,2,1};
        int[] s2 = {2,2};
        System.out.println(intersection(s1, s2));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        HashSet set = new HashSet();
        HashSet r = new HashSet();
        int i = 0;
        while (i < nums1.length) {
            set.add(nums1[i++]);
        }
        i = 0;
        while (i < nums2.length) {
            if (set.contains(nums2[i])) {
                r.add(nums2[i]);
            }
            i++;
        }
        return r.stream().mapToInt(value -> (Integer) value).toArray();
    }
}
