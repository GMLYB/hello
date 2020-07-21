package July;

import java.util.Arrays;

/*


给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,1]
输出: 1
示例 2:

输入: [4,1,2,1,2]
输出: 4

 */

public class LeetCode_136 {

    public int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        //排序
        Arrays.sort(nums);
        //用减法避免超出数组长度
        for (int i = 1; i < nums.length; i = i + 2) {
            if (nums[i] != nums[i-1]){
                return nums[i-1];
            }
        }
        return nums[nums.length-1];

    }

    /*
        异或算法
        2 ^ 2 = 0 ; 5 ^ 0 = 5  ----> 2 ^ 2 ^ 1 = 0 ^ 1 = 1
        10          101
        10          000
        --          ---
        00          101
     */

//    public int singleNumber(int[] nums) {
//
//        int num = 0;
//        for (Integer i : nums){
//            num = num ^ i;
//        }
//
//        return num;
//    }



    public static void main(String[] args) {
        LeetCode_136 code = new LeetCode_136();
//        int[] nums = {2, 2, 1};
        int[] nums = {4,1,2,1,2};
        System.out.println(code.singleNumber(nums));
    }
}
