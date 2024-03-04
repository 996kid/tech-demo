package leetcode.array;

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int[] w = new int[k];
        int begin = 0;
        int end = k - 1;
        int index = 0;
        BigDecimal avg = new BigDecimal(0);
        for (int i = 0; i < nums.length; i++) {
            if (i < end) {
                w[index++] = nums[i];
            } else if (i == end) {
                int sum = 0;
                for (int j = 0; j < k; j++) {
                    sum+=w[j];
                }
                avg = new BigDecimal(sum).divide(new BigDecimal(k), 5, BigDecimal.ROUND_HALF_UP);
            }
        }
    }
}