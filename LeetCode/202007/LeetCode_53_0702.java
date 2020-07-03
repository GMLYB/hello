package day7_02;

/*

    示例:
    输入: [-2,1,-3,4,-1,2,1,-5,4],
    输出: 6
    解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
    进阶:
    如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。


-2		        -2				                -2
-2,1		    -2,-2+1,1 			            1
1,-3		    1,1-3,-3				        1
1,-3,4		    1,1-3+4,4				        4
4,-1		    4,4-1,-1				        4
4,-1,2		    4,4-1+2,2				        5
4,-1,2,1		4-1+2,4-1+2+1,1			        6
....,-5		    4-1+2+1,	4-1+2+1-5,-5		6
.....,4		    4-1+2+1,4-1+2+1-5+4,4		    6
......,7		max,4-1+2+1-5+4+7,7		        12
......,-200		max,4-1+2+1-5+4+7-200,-200	    12
......,190		max,.....+190,190,			    190
......,-100		190,190-100,-100			    190


 */

public class LeetCode_53 {
    public int maxSubArray(int[] nums) {
        //先取第一个
        int max = nums[0];
        if (nums.length == 1){
            return max;
        }
        //从第 几个 起 的和
        int tmp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //tmp不断的累加下一个元素
            tmp += nums[i];
            //判断max 和 累加和 的大小
            max = Math.max(max,tmp);
            //若下一个元素大于最大值，直接从这个元素开始
            if (nums[i] >= max){
                max = nums[i];
                tmp = nums[i];
            }
            //若下一个元素的值比前面累加的和都要大，直接从这个数开始加起
            if(nums[i] > tmp){
                tmp = nums[i];
            }

        }

        return max;
    }


    public static void main(String[] args) {

        int[] nums = new int[]{8,-19,5,-4,20};
//        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        LeetCode_53 code_53 = new LeetCode_53();
        int i = code_53.maxSubArray(nums);
        System.out.println(i);
    }
}
