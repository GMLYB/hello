package August;

/*

数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。

示例 1:
输入: cost = [10, 15, 20]
输出: 15
解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。

示例 2:
输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
输出: 6
解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。

注意：
    cost 的长度将会在 [2, 1000]。
    每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
 */

public class LeetCode_746 {
    /*
        cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
        [1]
        [100]
        [1,1] = min([1],[100]) + 1 = 2
        [1,1] = min([100],[1,1]) + 1 = 3        return min(2,3)
        [1,1] = min([1,1],[1,1]) + 1 = 3        return min(3,3)
        [1,100] = min([1,1],[1,1]) + 100 = 103  return min(3,103)
        [1,1] = min([1,1],[1,100]) + 1 = 4      return min(4,103)

     */
    public static int minCostClimbingStairs(int[] cost) {

        int first = cost[0];
        int second = cost[1];
        if (cost.length == 2){
            return second>first?first:second;
        }
        for (int i = 2; i < cost.length; i++) {
            int tmp = second;
            second = Math.min(first,second) + cost[i];
            first = tmp;
        }
        return Math.min(first,second);
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{0,1,2,2};
        int[] nums2 = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(nums1));
    }

}
