package j1229;
/*
941. 有效的山脉数组
    给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
    让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
    A.length >= 3
    在 0 < i < A.length - 1 条件下，存在 i 使得：
    A[0] < A[1] < ... A[i-1] < A[i]
    A[i] > A[i+1] > ... > A[A.length - 1]

    示例 1：
    输入：[2,1]
    输出：false

    示例 2：
    输入：[3,5,5]
    输出：false

    示例 3：
    输入：[0,3,2,1]
    输出：true

提示：
    0 <= A.length <= 10000
    0 <= A[i] <= 10000

 */
public class LeetCode_941 {
    public boolean validMountainArray(int[] arr) {
        int head = -1;
        int foot = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= arr[i - 1]) {
                head = i - 1;
                break;
            }
        }
        if (head == -1) {
            return false;
        }

        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i] >= arr[i - 1]) {
                foot = i;
                break;
            }
        }
        if (head == foot) {
            return true;
        }

        return false;


    }
}
