package October;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/*
对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。

示例 1：
输入：A = [1,2,0,0], K = 34
输出：[1,2,3,4]
解释：1200 + 34 = 1234

示例 2：
输入：A = [2,7,4], K = 181
输出：[4,5,5]
解释：274 + 181 = 455

示例 3：
输入：A = [2,1,5], K = 806
输出：[1,0,2,1]
解释：215 + 806 = 1021

示例 4：
输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
输出：[1,0,0,0,0,0,0,0,0,0,0]
解释：9999999999 + 1 = 10000000000

提示：
1 <= A.length <= 10000
0 <= A[i] <= 9
0 <= K <= 10000
如果 A.length > 1，那么 A[0] != 0


 */
public class LeetCode_989 {
    public List<Integer> addToArrayForm(int[] A, int K) {
        //记录返回值
        List<Integer> list = new ArrayList<>();
        //从个位数开始记录
        Stack<Integer> stack = new Stack<>();
        //判断数组加完，K有剩余情况下，最后一个相加是不是大于10
        boolean pd = false;
        for (int i = A.length - 1; i >= 0; i--) {
            // k 没有除尽情况
            if (K != 0){
                int ge = K % 10;
                K = K / 10;
                // 个位数 + A[i] 大于 9 ，就加上超过10的部分
                if (A[i] + ge > 9){
                    stack.push(A[i] + ge - 10);
                    //数组没有用尽的情况下
                    if (i - 1 >= 0){
                        A[i-1] += (A[i] + ge) / 10;
                    }else {
                        stack.push((A[i] + ge) / 10);
                        pd = true;
                    }
                }else {
                    stack.push(A[i] + ge);
                }
            }else {
                if (A[i] > 9){
                    stack.push(A[i] - 10);
                    if (i - 1 >= 0){
                        A[i-1] += A[i]  / 10;
                    }else {
                        stack.push(A[i] / 10);
                        pd = true;
                    }
                }else {
                    stack.push(A[i]);
                }
            }
        }
        if (K != 0 && pd){
            K += stack.pop();
        }
        while (K != 0){
            stack.push(K % 10);
            K = K / 10;
        }
        while (!stack.empty()){
            list.add(stack.pop());
        }

        return list;

    }

    @Test
    public void test(){
        int[] nums1 = new int[]{0};
        int K1 = 23;

        int[] nums2 = new int[]{2,7,4};
        int K2 = 181;
        int[] nums3 = new int[]{9,9,9,9,9,9,9,9,9,9};
        int K3 = 1;
        List<Integer> list = addToArrayForm(nums1, K1);
        list.forEach(System.out::println);
    }
}
