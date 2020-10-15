package October;

import org.junit.Test;

import java.util.Arrays;

/*

给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。

示例 1：
输入：[-4,-1,0,3,10]
输出：[0,1,9,16,100]

示例 2：
输入：[-7,-3,2,3,11]
输出：[4,9,9,49,121]

提示：
1 <= A.length <= 10000
-10000 <= A[i] <= 10000
A 已按非递减顺序排序。

 */
public class LeetCode_977 {
    public int[] sortedSquares1(int[] A) {
        int[] nums = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            nums[i] = A[i] * A[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    public int[] sortedSquares(int[] A) {
        if (A.length == 1) {
            return new int[]{A[0] * A[0]};
        }
        int index = A.length - 1;
        for (int i = 1; i < A.length; i++) {
            if (Math.abs(A[i - 1]) < Math.abs(A[i])){
                index = i-1;
                break;
            }
        }
        int[] nums = new int[A.length];
        nums[0] = A[index] * A[index];
        int i = index + 1;
        int j = index - 1;
        for (int k = 1; k < A.length; k++) {
            int num1 = 10001;
            int num2 = 10001;
            if (i < A.length){
                num1 = A[i];
            }
            if (j >= 0){
                num2 = A[j];
            }
            System.out.println("Math.abs(num1) = " + Math.abs(num1));
            System.out.println("Math.abs(num2) = " + Math.abs(num2));
            if (Math.abs(num1) > Math.abs(num2)){
                nums[k] = num2 * num2;
                j--;
            }else {
                nums[k] = num1 * num1;
                i++;
            }
        }
        return nums;
    }
    @Test
    public void test(){
        int[] nums1 = new int[]{-4,-1,0,3,10};
        int[] nums2 = new int[]{-2,0};
        int[] nums3 = new int[]{0,3};
        for (int i : sortedSquares(nums3)) {
            System.out.print(i+" ");
        }
    }
}
