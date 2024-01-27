package leetcode.array;

class NumArray {

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

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */