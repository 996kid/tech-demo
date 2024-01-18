package leetcode.array;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description MaxProfit
 * @date 2023/12/12
 */

public class MaxProfit {

    /**
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * @param args
     */
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        /**
         * 遍历记录最小值
         * 记录最大差值
         * 出现更小的值，替换最小值，后面元素减最小值的差值只会比减之前的最小值更大
         * 而且需要同步替换最大差值
         */
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i : prices) {
            if (i < min) {
                min = i;
            } else if (i - min > maxProfit) {
                maxProfit = i - min;
            }
        }

        return maxProfit;




        //时间复杂度过高
        /*int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;*/
    }
}
