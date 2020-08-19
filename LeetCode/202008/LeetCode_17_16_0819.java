package August;
/*

    一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。
    在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
    给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
    注意：本题相对原题稍作改动

示例 1：
输入： [1,2,3,1]
输出： 4
解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。

示例 2：
输入： [2,7,9,3,1]
输出： 12
解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。

示例 3：
输入： [2,1,4,5,3,1,1,3]
输出： 12
解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。

 */

/*
    分析：[2,1,4,5,3,1,1,3]
    max[2] = max(2) = 2
    max[2,1] = max(2,1) = 2
    max[2,4] = max(max[2]+4, max[2,1]) = 2 + 4 = 6
    max[2,5] = max(max[2,1] + 5, max[2,4]) = 2 + 5 = 7
    max[2,3] = max(max[2,4] + 3, max[2,5]) = 6 + 3 = 9
    max[2,1] = max(max[2,5] + 1, max[2,3]) = 6 + 3 = 9
    max[2,1] = max(max[2,3] + 1, max[2,1]) = 9 + 1 = 10
    max[2,3] = max(max[2,1] + 3, max[2,1]) = 9 + 3 = 12
 */

public class LeetCode_17_16 {

    public static int massage(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        int first = nums[0];
        int second = Math.max(first,nums[1]);
        if (nums.length == 2){
            return second;
        }

        int max = 0;
        for (int i = 2; i < nums.length; i++) {
            // max[2,4] = max(max[2]+4, max[2,1]) = 2 + 4 = 6
            max = Math.max(first + nums[i],second);
            //下一个的first为max[2,1]
            first = second;
            //下一个的second为max[2,4]
            second = max;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,1,1,2};
        System.out.println(massage(nums));
    }
}
