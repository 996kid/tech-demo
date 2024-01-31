package leetcode.array;

import java.util.*;

public class Intersect {


    /**
     * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。
     * 返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致
     * （如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
     * 示例 1：
     *
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * 示例 2:
     *
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[4,9]
     *
     * 提示：
     * 1 <= nums1.length, nums2.length <= 1000
     * 0 <= nums1[i], nums2[i] <= 1000
     * @param args
     */
    public static void main(String[] args) {
        int[] a = new int[2];
    }

    /**
     * 双指针
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;

        List<Integer> r = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                r.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }
        return r.stream().mapToInt(value -> value).toArray();
    }

    /**
     * hash 表计数
     *     - 遍历其中一个数组 生成Hash  nums1[i] -> counter
     *     - 再遍历另外一个数组 判断是否在Hash中存在
     *          - 存在再判断counter 数量是否大于0 是则加入 否则继续
     *          - 不存在继续
     *
     *          输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     *          [1,2,2,1] [2,2]
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect1(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.get(nums1[i]) != null) {
                map.put(nums1[i], map.get(nums1[i]) + 1);
            } else {
                map.put(nums1[i], 1);
            }
        }
        List<Integer> r = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (map.get(nums2[i]) != null) {
                Integer counter = map.get(nums2[i]);
                if (counter >= 1) {
                    r.add(nums2[i]);
                    map.put(nums2[i], map.get(nums2[i]) - 1);
                }
            }
        }
        return r.stream().mapToInt(Integer::intValue).toArray();
    }
}
