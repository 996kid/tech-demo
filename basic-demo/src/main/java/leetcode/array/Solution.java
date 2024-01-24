package leetcode.array;

import java.util.ArrayList;
import java.util.List;

class Solution {

        public List<String> summaryRanges(int[] nums) {
            // f分段循环
            int low = 0;
            List<String> ret = new ArrayList<String>();
            for (int i = 0; i < nums.length - 1; i++) {
                low = i;
                while (i < nums.length - 1 && nums[i] + 1 == nums[i + 1]) {
                    i++;
                }
                StringBuilder sb = new StringBuilder();
                if (nums[low] == nums[i]) {
                    ret.add(nums[low] + "");
                } else {
                    sb.append(nums[low]).append("->").append(i);
                    ret.add(sb.toString());
                }
            }
            return ret;
        }
}