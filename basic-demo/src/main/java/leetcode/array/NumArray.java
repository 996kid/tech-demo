package leetcode.array;

public class NumArray {

    /**
     * 给定一个整数数组  nums，处理以下类型的多个查询:
     * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
     *
     * 实现 NumArray 类：
     * NumArray(int[] nums) 使用数组 nums 初始化对象
     * int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
     *
     * 示例 1：
     *
     * 输入：
     * ["NumArray", "sumRange", "sumRange", "sumRange"]
     * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
     * 输出：
     * [null, 1, -1, -3]
     *
     * 解释：
     * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
     * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
     * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
     * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
     * @param args
     */
    public static void main(String[] args) {

    }


    private int[] arr;

    /**
     * 初始化时计算好前缀和
     * @param nums
     */
    public NumArray(int[] nums) {
        arr = new int[nums.length + 1];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                arr[0] = 0;
            } else {
                arr[i] = sum;
            }
            sum += nums[i];
        }
        arr[nums.length] = sum;
    }

    public int sumRange(int left, int right) {
        return arr[right + 1] - arr[left];
    }

    // 最容易想到的
//    private final int[] arr;
//
//    public NumArray(int[] nums) {
//        arr = nums;
//    }
//
//    public int sumRange(int left, int right) {
//        int sum = 0;
//        for (int i = left; i <= right; i++) {
//            sum += arr[i];
//        }
//        return sum;
//    }
}


