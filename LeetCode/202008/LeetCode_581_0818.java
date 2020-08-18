package August;

import java.util.Arrays;

/**
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 * 示例 1:
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 * 说明 :
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 */
public class LeetCode_581 {

    //false 有时间再调了..
    public static int findUnsortedSubarray(int[] nums) {
        int head = nums.length - 1;
        int foot = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i-1]){
                head = i-1;
                break;
            }
        }
        if (head == nums.length - 1){
            return 0;
        }
        for (int i = nums.length-1; i >= head; i--) {
            if (nums[i] < Math.max(nums[head],nums[i-1])){
                foot = i;
                break;
            }
        }

        for (int i = head; i >= 0; i--) {
            if (nums[i] < nums[foot]){
                head = i;
                break;
            }
        }

        return foot-head + 1;
    }

    // 8 ms 41MB
    public static int findUnsortedSubarray1(int[] nums) {
        //复制一份
//        int[] copyarr = Arrays.copyOf(nums,nums.length);
        int[] copyarr = nums.clone();
        //排序
        Arrays.sort(copyarr);
        int head = nums.length - 1;
        int foot = 0;
        //从头开始，逐个比较，找出第一个不同的下标
        for (int i = 0; i < nums.length; i++) {
            if (copyarr[i] != nums[i]){
                head = i;
                break;
            }
        }
        //要是一直到最后一个，就代表两个数组都相同，都是升序的数组
        if (head == nums.length - 1){
            return 0;
        }
        //从尾部开始，逐个比较，找出第一个不同的下标
        for (int i = nums.length-1; i >= head; i--) {
            if (copyarr[i] != nums[i]){
                foot = i;
                break;
            }
        }
        //例如 下标 2 到下标 5 ，有 5 - 2 + 1 = 4 个值
        return foot - head + 1;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 6, 4, 8, 10, 9, 15};
        int[] nums2 = new int[]{1,2,3,4};
        int[] nums3 = new int[]{1,3,2,2,2};
        int[] nums4 = new int[]{2,3,3,2,4};
        System.out.println(findUnsortedSubarray(nums4));
    }
}
