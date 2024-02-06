package leetcode.array;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 996kid@gmail.com
 * @date 2024/2/5
 */
public class NextGreaterElement {


    public static void main(String[] args) {
        int[] a = {4,1,2};
        int[] b = {1,3,4,2};
        nextGreaterElement(a, b);
    }

    // [4,1,2] [1,3,4,2]
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        int index = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }
        for (int i = 0; i < nums1.length; i++) {
            int targetIndex = map.get(nums1[i]);
            if (targetIndex == nums2.length - 1) {
                result[index++] = -1;
            } else {
                for (int j = targetIndex + 1; j < nums2.length; j++) {
                    if (nums2[j] > nums1[i]) {
                        result[index++] = nums2[j];
                        break;
                    }
                    if (j == nums2.length - 1) {
                        result[index++] = -1;
                    }
                }

            }
        }
        return result;
    }

    /**
     * 单调递增栈（栈顶到栈底）范式
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        // {2,5,3,6,8,4,7,1}
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Deque<Integer> stack = new ArrayDeque<Integer>();
        // 从后往前遍历数组
        for (int i = nums2.length - 1; i >= 0; --i) {
            int num = nums2[i];
            // 将比当前小的数出栈，直到一个比当前数大的数则停止循环
            // 保证留在栈里的都是比当前数大的数，且是栈顶到栈底是递增的
            while (!stack.isEmpty() && num >= stack.peek()) {
                stack.pop();
            }
            // 栈顶元素比当前元素大，根据栈的特性知道栈顶元素是右边第一个较大的
            map.put(num, stack.isEmpty() ? -1 : stack.peek());
            stack.push(num);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

}
