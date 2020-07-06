package July;

/*
给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
注意：你不能在买入股票前卖出股票。

示例 1:
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。

示例 2:
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */

public class LeetCode_121 {
    public int maxProfit(int[] prices) {
//        长度小于2，直接返回
        if (prices.length <= 1){
            return 0;
        }
        int value = 0;
        int max = prices[0];
        int min = prices[0];
        for (int i = 0; i < prices.length; i++) {
            //找到价格比之前低，重新计价
            if (prices[i] < min){
                max = prices[i];
                min = prices[i];
            }
            //要是比之前的更高，就拿今天算最高
            if (max < prices[i]){
                max = prices[i];
            }
            //差价比之前的差价大，就拿这次的差价
            if ((max - min) > value){
                value = max-min;
            }
        }
        return value;

    }

    public static void main(String[] args) {
        LeetCode_121 code121 = new LeetCode_121();
        int[] prices = new int[]{7,1,5,3,6,4};
//        int[] prices = new int[]{1,2};

        System.out.println(code121.maxProfit(prices));
    }
}
