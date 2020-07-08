package July;

/*
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

示例 1:
输入: [7,1,5,3,6,4]
输出: 7
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。

示例 2:
输入: [1,2,3,4,5]
输出: 4
解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。

示例 3:
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 
提示：
1 <= prices.length <= 3 * 10 ^ 4
0 <= prices[i] <= 10 ^ 4
 */

public class LeetCode_122 {
    public int maxProfit(int[] prices) {

        if (prices.length <= 1) {
            return 0;
        } else if (prices.length == 2) {
            return (prices[1] - prices[0]) > 0 ? (prices[1] - prices[0]) : 0;
        }
        int sum = 0;
        int max = prices[0];
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            //3 5 1 情况，因为有max = prices[i]的操作，所以3 5 1在第三个判断不会执行
            if (prices[i] < min){
                sum += max-min;
                min = prices[i];
                max = prices[i];
            }
            // 1 3 5 情况，5就是最大值
            if (prices[i] > max){
                max = prices[i];
            }
            // 1 5 3 情况 3 小于 5 ，就在5的时候卖
            if (prices[i] < max){
                sum += max-min;
                min = prices[i];
                max = prices[i];
            }
        }
        //要是价钱一直涨，最后一天卖出去
        if(max > min){
            sum += max-min;
        }

        return sum;
    }

    public static void main(String[] args) {

        int[] prices = new int[]{7,6,4,3,1};
//        int[] prices = new int[]{7,1,5,3,6,4};
//        int[] prices = new int[]{1,2,3,4,5};
//        int[] prices = new int[]{2,4,1};
        LeetCode_122 code122 = new LeetCode_122();
        System.out.println(code122.maxProfit(prices));

    }
}
