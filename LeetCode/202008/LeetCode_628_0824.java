package August;

import java.util.Arrays;


/*

    给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。

示例 1:
输入: [1,2,3]
输出: 6

示例 2:
输入: [1,2,3,4]
输出: 24
注意:
给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。

 */
public class LeetCode_628 {
    public int maximumProduct(int[] nums) {
        int length = nums.length;
        if (length == 3){
            return nums[0] * nums[1] * nums[2];
        }
        Arrays.sort(nums);
        //只有一个小于 0 或者 全小于0
        // [-100 1 2 3], [1 2 3 4], [-4 -3 -2 -1]
        if (nums[1] > 0 || nums[length - 1] < 0){
            return nums[length - 1] * nums[length - 2] * nums[length - 3];
        }

        int num1 = nums[length - 1] * nums[length - 2] * nums[length - 3];
        int num2 = nums[length - 1] * nums[0] * nums[1];
        return num1 > num2 ? num1 : num2;

    }

    public int maximumProduct1(int[] nums) {
        int length = nums.length;
        if (length == 3){
            return nums[0] * nums[1] * nums[2];
        }
        Arrays.sort(nums);
        int num1 = nums[length - 1] * nums[length - 2] * nums[length - 3];
        int num2 = nums[length - 1] * nums[0] * nums[1];
        return Math.max(num1,num2);

    }
}
