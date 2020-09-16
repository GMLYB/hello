package September;


/*
给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
你可以返回满足此条件的任何数组作为答案。

示例：
输入：[3,1,2,4]
输出：[2,4,3,1]
输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 
提示：
1 <= A.length <= 5000
0 <= A[i] <= 5000

 */
public class LeetCode_905 {
    public int[] sortArrayByParity(int[] A) {

        if (A.length <= 1) {
            return A;
        }

        int i = 0;
        int j = A.length - 1;
        while (i <= j) {
            if (A[i] % 2 == 0) {
                i++;
            } else if (A[j] % 2 == 1) {
                j--;
            } else if (A[i] % 2 == 1 && A[j] % 2 == 0) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
                i++;
                j--;
            }
        }

        return A;
    }
}
