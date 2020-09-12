package September;

import org.junit.Test;

/*
如果数组是单调递增或单调递减的，那么它是单调的。
如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
当给定的数组 A 是单调数组时返回 true，否则返回 false。

示例 1：
输入：[1,2,2,3]
输出：true

示例 2：
输入：[6,5,4,4]
输出：true

示例 3：
输入：[1,3,2]
输出：false

示例 4：
输入：[1,2,4,5]
输出：true

示例 5：
输入：[1,1,1]
输出：true
 
 */
public class LeetCode_896 {
    public boolean isMonotonic(int[] A) {
        if (A.length <= 2) {
            return true;
        }
        boolean up = false;
        boolean down = false;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                up = true;
            }
            if (A[i] < A[i - 1]) {
                down = true;
            }
            if (up) {
                if (A[i] < A[i - 1]) {
                    return false;
                }
            }
            if (down) {
                if (A[i] > A[i - 1]) {
                    return false;
                }
            }
        }

        return false;
    }

    public boolean isMonotonic1(int[] A) {
        int i = 0;
        int j = 0;
        for (int k = 0; k < A.length - 1; k++) {
            if (A[k] < A[k + 1]) {
                i++;
            } else if (A[k] > A[k + 1]) {
                j++;
            }
            if (i != 0 && j != 0) {
                return false;
            }
        }
        return i * j == 0;
    }

    @Test
    public void test() {

    }
}
