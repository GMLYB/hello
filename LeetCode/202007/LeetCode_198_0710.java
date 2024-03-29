package July;

/*
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

示例 1：
输入：[1,2,3,1]
输出：4
解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。

示例 2：
输入：[2,7,9,3,1]
输出：12
解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。

提示：
0 <= nums.length <= 100
0 <= nums[i] <= 400

 */

public class LeetCode_198 {

    public int rob(int[] nums) {
        if (nums.length <= 2){
            if (nums.length == 0){
                return 0;
            }
            if (nums.length == 1){
                return nums[0];
            }
            if (nums.length == 2){
                return nums[0] > nums[1] ? nums[0] : nums[1];
            }
        }
        // f(3) = max(f(1) + num[2] , f(2))
        // f(4) = max(f(2) + num[3] , f(3))
        //1,2,3,1
        int max = Math.max(nums[0],nums[1]);//f(2)
        int premax = nums[0];//f(1)
        for (int i = 2; i < nums.length; i++) {
            int tmp = premax + nums[i];// f(1) + num[2]
            premax = max; // premax = f(2)
            max = Math.max(max,tmp); //max(f(2), f(1) + num[2])
        }
        return max;
    }

    public static void main(String[] args) {
        LeetCode_198 code = new LeetCode_198();
//        int[] nums = new int[]{1,2,3,1};
        int[] nums = new int[]{2,7,9,3,1};
        System.out.println(code.rob(nums));
    }
}
