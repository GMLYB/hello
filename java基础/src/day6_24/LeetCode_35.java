package day6_24;

import java.util.List;

/**
 *
 *
 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 你可以假设数组中无重复元素。

 示例 1:
 输入: [1,3,5,6], 5
 输出: 2

 示例 2:
 输入: [1,3,5,6], 2
 输出: 1

 示例 3:
 输入: [1,3,5,6], 7
 输出: 4

 示例 4:
 输入: [1,3,5,6], 0
 输出: 0

 *
 */

public class LeetCode_35 {

//    public int searchInsert(int[] nums, int target) {
//
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] >= target){
//                return i;
//            }
//        }
//        return nums.length;
//    }


    //二分法
    public int searchInsert(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;

        while (start <= end){
            int mid = (start + end) / 2;

            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                end = mid - 1;
            }else {
                start = mid +1;
            }
        }
        return start;
    }



    public static void main(String[] args) {
        LeetCode_35 cod35 = new LeetCode_35();

        int[] nums = {1,3,5,6};
        int target = 2;

        int i = cod35.searchInsert(nums, target);

        System.out.println(i);
    }

}
