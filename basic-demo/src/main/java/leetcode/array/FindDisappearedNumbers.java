package leetcode.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 996kid@gmail.com
 * @date 2024/1/30
 */
public class FindDisappearedNumbers {

    /**
     * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
     *
     * 示例 1：
     *
     * 输入：nums = [4,3,2,7,8,2,3,1]
     * 输出：[5,6]
     * 示例 2：
     *
     * 输入：nums = [1,1]
     * 输出：[2]
     *
     * 提示：
     *
     * n == nums.length
     * 1 <= n <= 105
     * 1 <= nums[i] <= n
     * 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
     * @param args
     */
    public static void main(String[] args) {
        
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        List<Integer> r = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                r.add(i);
            }
        }
        return r;
    }

    /**
     * 我们可以用一个哈希表记录数组 nums\textit{nums}nums 中的数字，由于数字范围均在 [1,n][1,n][1,n] 中，记录数字后我们再利用哈希表检查 [1,n][1,n][1,n] 中的每一个数是否出现，从而找到缺失的数字。
     *
     * 由于数字范围均在 [1,n][1,n][1,n] 中，我们也可以用一个长度为 nnn 的数组来代替哈希表。这一做法的空间复杂度是 O(n)O(n)O(n) 的。我们的目标是优化空间复杂度到 O(1)O(1)O(1)。
     *
     * 注意到 nums 的长度恰好也为 nnn，能否让 nums 充当哈希表呢？
     *
     * 由于 nums 的数字范围均在 [1,n] 中，我们可以利用这一范围之外的数字，来表达「是否存在」的含义。
     *
     * 具体来说，遍历 nums，每遇到一个数 x，就让 nums[x−1] 增加 n。由于 nums中所有数均在 [1,n] 中，增加以后，这些数必然大于 nnn。
     * 最后我们遍历 nums，若 nums[i] 未大于 nnn，就说明没有遇到过数 i+1。这样我们就找到了缺失的数字。
     *
     * 注意，当我们遍历到某个位置时，其中的数可能已经被增加过，因此需要对 nnn 取模来还原出它本来的值。
     *
     * @param nums
     * @return
     */
    public static List<Integer> findDisappearedNumbers1(int[] nums) {

        return null;
    }
}
