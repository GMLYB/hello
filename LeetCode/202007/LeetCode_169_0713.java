package July;
/*
给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1:
输入: [3,2,3]
输出: 3

示例 2:
输入: [2,2,1,1,1,2,2]
输出: 2

 */

import java.util.HashMap;
import java.util.Map;

public class LeetCode_169 {
    public int majorityElement(int[] nums) {
        int limit = nums.length/2;
        Map<Integer,Integer> map = new HashMap<>();
        for(Integer num: nums){
            if (map.containsKey(num)){
                int tmpnum = map.get(num) +1;
                if (tmpnum > limit){
                    return num;
                }
                map.put(num,tmpnum);
            }else {
                map.put(num,1);
            }
        }
        return nums[0];
    }

    public static void main(String[] args) {

        LeetCode_169 code = new LeetCode_169();
//        int[] nums = {3,2,3};
//        int[] nums = {2,2,1,1,1,2,2};
        int[] nums = {2,2};
        System.out.println(code.majorityElement(nums));
    }
}
