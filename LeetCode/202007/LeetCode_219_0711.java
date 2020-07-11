package July;

import java.util.HashMap;
import java.util.Map;

/*
给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。

示例 1:
输入: nums = [1,2,3,1], k = 3
输出: true

示例 2:
输入: nums = [1,0,1,1], k = 1
输出: true

示例 3:
输入: nums = [1,2,3,1,2,3], k = 2
输出: false

 */

public class LeetCode_219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){//有这个数字
                if (i - map.get(nums[i]) <= k){ //判断两个之间的距离 和 k比较
                    return true;
                }else {//大于k的话，给最后的位置
                    map.put(nums[i],i);
                }
            }else {//没有就添加进去
                map.put(nums[i],i);
            }
        }
        return false;

    }

    public static void main(String[] args) {
        LeetCode_219 code = new LeetCode_219();
        int[] nums = new int[]{1,2,3,1};
        int k = 3;
//        int[] nums = new int[]{1,0,1,1};
//        int k = 1;
//        int[] nums = new int[]{1,2,3,1,2,3};
//        int k = 2;

        System.out.println(code.containsNearbyDuplicate(nums,k));
    }
}
