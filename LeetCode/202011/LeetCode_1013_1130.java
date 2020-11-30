package November;

import org.junit.Test;
/*

1013. 将数组分成和相等的三个部分
给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
形式上，如果可以找出索引 i+1 < j 且满足
    A[0] + A[1] + ... + A[i]
    == A[i+1] + A[i+2] + ... + A[j-1]
    == A[j] + A[j-1] + ... + A[A.length - 1]
就可以将数组三等分。

示例 1：
输入：[0,2,1,-6,6,-7,9,1,2,0,1]
输出：true
解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1

示例 2：
输入：[0,2,1,-6,6,7,9,-1,2,0,1]
输出：false

示例 3：
输入：[3,3,6,5,-2,2,5,1,-9,4]
输出：true
解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4

提示：
3 <= A.length <= 50000
-10^4 <= A[i] <= 10^4

 */
public class LeetCode_1013 {
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        for (int i : A) {
            sum += i;
        }
        if (sum % 3 != 0) {
            return false;
        }
        sum = sum / 3;

        int ci = 0;
        int tmp = 0;

        for (int i = 0; i < A.length; i++) {
            tmp += A[i];
            if (tmp == sum){
                ci++;
                tmp = 0;
            }
            if (ci == 2 && i != A.length - 1){
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[] A1 = new int[]{0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1};
        int[] A2 = new int[]{0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1};
        int[] A3 = new int[]{3, 3, 6, 5, -2, 2, 5, 1, -9, 4};
        System.out.println(canThreePartsEqualSum(A1));
    }
}
