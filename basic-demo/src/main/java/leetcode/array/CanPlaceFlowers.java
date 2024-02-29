package leetcode.array;

/**
 * @author 996kid@gmail.com
 * @date 2024/2/19
 */
public class CanPlaceFlowers {

    /**
     * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     * 给你一个整数数组 flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，
     * 能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false 。
     *
     * 示例 1：
     *
     * 输入：flowerbed = [1,0,0,0,1], n = 1
     * 输出：true
     * 示例 2：
     *
     * 输入：flowerbed = [1,0,0,0,1], n = 2
     * 输出：false
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * 最大能种多少花？
     * 001000100001000001000   000100
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        /**
         * 1. j - i >= 4
         * 2. 种植范围 [i+2, j-2]
         * 3. j - i - 3
         *     - 奇数  最大可以种植的花  (j - i - 3) / 2 + 1
         *     - 偶数  最大可以种植的花               (j - i - 3) / 2
         *
         *
         *     [1,0,0,0,0,1]
         *     [0,0,1,0,1] 0
         *     [0,0,0,1,0,1] 1
         *     [0,0,0,0,1,0,1] 2
         *     [0,0,0,0,0,1,0,1] 3
         *     [0,0,0,0,0,0,1,0,1]
         *
         *     [1,0,0,0,1,0,0]  7 - 4 - 3
         *     [1,0,0,0,1,0,0,0]
         *
         *     [1,0]
          * */
        int prev = -1;
        int max = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                if (i - prev >= 3) {
                    if (prev < 0) {
                        max += (i - prev - 3) / 2 + 1;
                    } else if ((i - prev - 3) % 2 == 0) {
                        max += (i - prev - 3) / 2;
                    } else {
                        max += (i - prev - 3) / 2 + 1;
                    }
                }
                prev = i;
            }
        }

        if (flowerbed[flowerbed.length - 1] != 1) {
            if (prev >= 0) {
                int i = flowerbed.length - prev - 3;
                if (i >= 0) {
                    max += i / 2 + 1;
                }
            } else {
                if (flowerbed.length % 2 == 0) {
                    return flowerbed.length / 2 >= n;
                } else {
                    return (flowerbed.length / 2 + 1) >= n;
                }
            }
        }
        if (flowerbed.length == 1 && flowerbed[0] == 0 && n == 1) {
            return true;
        }
        return max >= n;
    }

    class Solution {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            int count = 0;
            int m = flowerbed.length;
            int prev = -1;
            for (int i = 0; i < m; i++) {
                if (flowerbed[i] == 1) {
                    if (prev < 0) {
                        count += i / 2;
                    } else {
                        count += (i - prev - 2) / 2;
                    }
                    prev = i;
                }
            }
            if (prev < 0) {
                count += (m + 1) / 2;
            } else {
                count += (m - prev - 1) / 2;
            }
            return count >= n;
        }
    }

}
