package August;

/*
给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。

示例 1:
输入: nums = [4,2,3]
输出: true
解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。

示例 2:
输入: nums = [4,2,1]
输出: false
解释: 你不能在只改变一个元素的情况下将其变为非递减数列。

说明：
1 <= n <= 10 ^ 4
- 10 ^ 5 <= nums[i] <= 10 ^ 5
 */
public class LeetCode_665 {
    /*
         2 5 5 2 6
         2 3 5 2 6
         2 5 6 2 3
         3 4 2 3
     */
    public boolean checkPossibility(int[] nums) {
        //统计出现情况的次数 要是次数大于2次，就false
        int count = 0;
        for(int i = 1;i < nums.length;i++){
            //出现 【4 3】 递减的情况
            if(nums[i] < nums[i-1]){
                count++;
                if(i > 1){
                    // i>1 是为了防止坐标越界 要是出现 【3 4 2 3】 这种情况，2 比 4 小，2 又比 4 前面的 3 小，次数先多加一次，但是遇到下面两种情况，多加的次数要减掉
                    if(nums[i] < nums[i-2]){
                        count++;
                        if(i < nums.length - 1){
                            /* 情况一：
                                i < nums.length -1 是为了防止越界 ,
                                出现【3 4 2 5】这种情况，虽然 2 比 4 小，2 还比 4 前面的 3 小，但是 5 大于 4 ，可以把 2 转换为 4 ，
                                证明出现这种情况也是可行的
                            */
                            if(nums[i+1] >= nums[i-1]){
                                count--;
                            }
                        }else {
                            /*
                            情况二：
                                else的情况即为 i == nums.length 的情况，处在了最后的位置
                                    例如【1 2 3 4 2】，最后的 2 比 4 小， 也比 3 小，但是处在最后一个位置
                                    所以可以把 2 转换为 5 ，这样也是可行的
                             */
                            count--;
                        }
                    }
                }
                if(count >= 2){
                    return false;
                }
            }
        }
        return true;
    }
}
