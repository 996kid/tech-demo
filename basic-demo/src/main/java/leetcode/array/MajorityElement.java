package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description MajorityElement
 * @date 2024/1/16
 */

public class MajorityElement {

    /**
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
     *
     * 1. hash 表
     * 2. 排序
     * 3. 随机化
     * 4. 分治
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {0,2,3,3,3,3,0};
        majorityElement1(arr);
    }

    public static int majorityElement1(int[] nums) {
        // hash 表计数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > (nums.length / 2)) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * 因为超过 n/2 的数组下标被众数占据了，这样我们随机挑选一个下标对应的元素并验证，有很大的概率能找到众数。
     *
     * 算法
     *
     * 由于一个给定的下标对应的数字很有可能是众数，我们随机挑选一个下标，检查它是否是众数，如果是就返回，否则继续随机挑选。
     *
     * @param nums
     * @return
     */
    public static int majorityElement2(int[] nums) {
        return 0;
    }

    /**
     * nums[n/2] 必然是众数
     * @param nums
     * @return
     */
    public static int majorityElement3(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    /**
     * 如果数 a 是数组 nums 的众数，如果我们将 nums 分成两部分，那么 a 必定是至少一部分的众数。
     *
     * 我们可以使用反证法来证明这个结论。假设 a 既不是左半部分的众数，也不是右半部分的众数，那么 a 出现的次数少于 l / 2 + r / 2 次，其中 l 和 r 分别是左半部分和右半部分的长度。由于 l / 2 + r / 2 <= (l + r) / 2，说明 a 也不是数组 nums 的众数，因此出现了矛盾。所以这个结论是正确的。
     *
     * 这样以来，我们就可以使用分治法解决这个问题：将数组分成左右两部分，分别求出左半部分的众数 a1 以及右半部分的众数 a2，随后在 a1 和 a2 中选出正确的众数。
     *
     * 算法
     * 我们使用经典的分治算法递归求解，
     * 直到所有的子问题都是长度为 1 的数组。长度为 1 的子数组中唯一的数显然是众数，直接返回即可。
     * 如果回溯后某区间的长度大于 1，我们必须将左右子区间的值合并。
     * 如果它们的众数相同，那么显然这一段区间的众数是它们相同的值。否则，我们需要比较两个众数在整个区间内出现的次数来决定该区间的众数。
     *
     *
     */
    public static int majorityElement4(int[] nums) {
        
        return 0;
    }


    /**
     * 如果我们把众数记为 +1，把其他数记为 −1，将它们全部加起来，显然和大于 0，从结果本身我们可以看出众数比其他数多
     * @param nums
     * @return
     */
    public static int majorityElement5(int[] nums) {

        return 0;
    }
}
