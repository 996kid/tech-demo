package leetcode.array;

import java.util.*;

/**
 * @author 996kid@gmail.com
 * @date 2024/1/29
 */
public class ThirdMax {

    /**
     * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
     *
     * 示例 1：
     *
     * 输入：[3, 2, 1]
     * 输出：1
     * 解释：第三大的数是 1 。
     * 示例 2：
     *
     * 输入：[1, 2]
     * 输出：2
     * 解释：第三大的数不存在, 所以返回最大的数 2 。
     * 示例 3：
     *
     * 输入：[2, 2, 3, 1]
     * 输出：1
     * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
     * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
     *
     * 提示：
     *
     * 1 <= nums.length <= 104
     * -231 <= nums[i] <= 231 - 1
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * 排序后查找第三大的数
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            set.add(nums[i]);
            if (set.size() == 3) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }

    /**
     * 有序集合
     *      - 维护大小为3的有序集合
     *      - 遍历数组将元素添加到
     * @param nums
     * @return
     */
    public int thirdMax1(int[] nums) {
        TreeSet<Integer> s = new TreeSet<>();
        for (int num : nums) {
            s.add(num);
            if (s.size() > 3) {
                s.remove(s.first());
            }
        }
        return s.size() == 3 ? s.first() : s.last();
    }

    /**
     * 我们可以遍历数组，并用三个变量 a、b 和 c 来维护数组中的最大值、次大值和第三大值，以模拟方法二中的插入和删除操作。为方便编程实现，我们将其均初始化为小于数组最小值的元素，视作「无穷小」，比如 −2^63
     * 遍历数组，对于数组中的元素 num：
     *
     * 若 num>a，我们将 c 替换为 b，b 替换为 a，a 替换为 num，这模拟了将 num 插入有序集合，并删除有序集合中的最小值的过程；
     * 若 a>num>b，类似地，我们将 c 替换为 b，b 替换为 num，a 保持不变；
     * 若 b>num>c，类似地，我们将 c 替换为 num，a 和 b 保持不变；
     * 其余情况不做处理。
     * 遍历结束后，若 c 仍然为 −2^63 则说明数组中不存在三个或三个以上的不同元素，即第三大的数不存在，返回 a，否则返回 c。
     * [1,2]
     * @param nums
     * @return
     */
    public int thirdMax2(int[] nums) {
        long a = Long.MIN_VALUE, b = Long.MIN_VALUE, c = Long.MIN_VALUE;
        for (long i : nums) {
            if (i > a) {
                c = b;
                b = a;
                a = i;
            } else if (i < a && i > b) {
                c = b;
                b = i;
            } else if (i < b && i > c) {
                c = i;
            }
        }
        if (c == Long.MIN_VALUE) {
            return (int) a;
        }
        return (int) c;
    }
}
