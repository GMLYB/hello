package October;

import org.junit.Test;

import java.util.Arrays;

/*
在大小为 2N 的数组 A 中有 N+1 个不同的元素，其中有一个元素重复了 N 次。
返回重复了 N 次的那个元素。

示例 1：
输入：[1,2,3,3]
输出：3

示例 2：
输入：[2,1,2,5,3,2]
输出：2

示例 3：
输入：[5,1,5,2,5,3,5,4]
输出：5
 
提示：
4 <= A.length <= 10000
0 <= A[i] < 10000
A.length 为偶数

 */
public class LeetCode_961 {

    public int repeatedNTimes(int[] A) {
        Arrays.sort(A);
        if (A[A.length / 2 + 1] == A[A.length / 2]) {
            return A[A.length / 2];
        }
        return A[(A.length / 2) - 1];
    }

    public int repeatedNTimes1(int[] A) {
        int[] nums = new int[10000];
        int maxnum = 0;
        int num = 0;
        for (int i : A) {
            nums[i]++;
            if (nums[i] > maxnum) {
                num = i;
                maxnum = nums[i];
            }
            if (maxnum == A.length / 2) {
                return num;
            }
        }
        return num;
    }

    @Test
    public void test() {
        int[] A = new int[]{9, 5, 3, 3};
        System.out.println(repeatedNTimes(A));
    }
}
