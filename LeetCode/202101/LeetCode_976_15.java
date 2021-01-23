package January;

import org.junit.Test;

import java.util.Arrays;

/*

976. 三角形的最大周长
给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。

如果不能形成任何面积不为零的三角形，返回 0。



示例 1：

输入：[2,1,2]
输出：5
示例 2：

输入：[1,2,1]
输出：0
示例 3：

输入：[3,2,3,4]
输出：10
示例 4：

输入：[3,6,2,3]
输出：8


提示：

3 <= A.length <= 10000
1 <= A[i] <= 10^6

 */

public class LeetCode_976 {

    //超时了。。。。
    public int largestPerimeter(int[] A) {

        //先排序
        Arrays.sort(A);

        //最大周长
        int maxC = 0;

        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                for (int k = j + 1; k < A.length; k++) {
                    if (A[i] + A[j] <= A[k]) {
                        break;
                    }
                    maxC = Math.max(maxC, A[i] + A[j] + A[k]);
                }
            }
        }

        return maxC;
    }

    //上面的超时了，那就从大到小，第一个可以的最大 ...还是超时了，比上面稍微好了一点
    public int largestPerimeter2(int[] A) {

        //先排序
        Arrays.sort(A);

        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (A[k] + A[j] > A[i]) {
                        return A[i] + A[j] + A[k];
                    }
                }
            }
        }

        return 0;
    }

    // 排好序了， 直接算相邻的就好了。。
    public int largestPerimeter3(int[] A) {

        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            int a = A[i];
            int b = A[i - 1];
            int c = A[i - 2];
            if (a < b + c) {
                return a + b + c;
            }
        }

        return 0;
    }


    @Test
    public void test() {
        int[] nums1 = new int[]{2, 1, 2};
        System.out.println(largestPerimeter2(nums1));
    }


}
