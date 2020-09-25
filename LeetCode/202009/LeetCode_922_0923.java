package September;

/*

给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
你可以返回任何满足上述条件的数组作为答案。

示例：
输入：[4,2,5,7]
输出：[4,5,2,7]
解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 
提示：
2 <= A.length <= 20000
A.length % 2 == 0
0 <= A[i] <= 1000

*/

public class LeetCode_922 {
    public int[] sortArrayByParityII(int[] A) {

        int ou = 0;
        int ji = 1;

        while (ji < A.length || ou < A.length) {
            while (ji < A.length && A[ji] % 2 != 0) {
                ji += 2;
            }

            while (ou < A.length && A[ou] % 2 == 0) {
                ou += 2;
            }
            if (ji > A.length || ou > A.length){
                return A;
            }

            int tmp = A[ji];
            A[ji] = A[ou];
            A[ou] = tmp;
            ji += 2;
            ou += 2;

        }

        return A;

    }
}
