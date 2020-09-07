package September;

import org.junit.Test;
/*

给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。

示例 1：
输入：
nums = [1, 7, 3, 6, 5, 6]
输出：3
解释：
索引 3 (nums[3] = 6) 的左侧数之和 (1 + 7 + 3 = 11)，与右侧数之和 (5 + 6 = 11) 相等。
同时, 3 也是第一个符合要求的中心索引。

示例 2：
输入：
nums = [1, 2, 3]
输出：-1
解释：
数组中不存在满足此条件的中心索引。
 
说明：
nums 的长度范围为 [0, 10000]。
任何一个 nums[i] 将会是一个范围在 [-1000, 1000]的整数。

 */
public class LeetCode_724 {

    public int pivotIndex(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = 0;
        for (int i = 1; i < nums.length; i++) {
            right += nums[i];
        }
        //扫描
        for (int i = 1; i < nums.length; i++) {
            if (left == right) {
                return i - 1;
            }
            left += nums[i - 1];
            right -= nums[i];
        }
        //判断最后一个
        if (left == right) {
            return nums.length - 1;
        }

        return -1;
    }
    /*
        官方思路：
            S 是数组的和，当索引 i 是中心索引时，位于 i 左边数组元素的和 leftsum 满足 S - nums[i] - leftsum。
            我们只需要判断当前索引 i 是否满足 leftsum==S-nums[i]-leftsum 并动态计算 leftsum 的值。

                public int pivotIndex(int[] nums) {
                    int sum = 0, leftsum = 0;
                    for (int x: nums) sum += x;
                    for (int i = 0; i < nums.length; ++i) {
                        if (leftsum == sum - leftsum - nums[i]) return i;
                        leftsum += nums[i];
                    }
                    return -1;
                }

     */

    @Test
    public void test() {
        int[] nums1 = {1, 7, 3, 6, 5, 6};
        int[] nums2 = {-1, -1, -1, -1, -1, -1};
        int[] nums3 = {-1, -1, -1, -1, -1, 0};
        int[] nums4 = {1, 2, 3};
        int[] nums5 = {-1, -1, 0, 0, -1, -1};
        int[] nums6 = {-1, -1, 0, 1, 1, 0};
        int i = pivotIndex(nums6);
        System.out.println(i);
    }
}
