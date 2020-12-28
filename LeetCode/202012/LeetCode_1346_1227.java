package December;

import org.junit.Test;

import java.util.Arrays;

/*

1346. 检查整数及其两倍数是否存在
给你一个整数数组 arr，请你检查是否存在两个整数 N 和 M，满足 N 是 M 的两倍（即，N = 2 * M）。
更正式地，检查是否存在两个下标 i 和 j 满足：
i != j
0 <= i, j < arr.length
arr[i] == 2 * arr[j]

示例 1：
输入：arr = [10,2,5,3]
输出：true
解释：N = 10 是 M = 5 的两倍，即 10 = 2 * 5 。

示例 2：
输入：arr = [7,1,14,11]
输出：true
解释：N = 14 是 M = 7 的两倍，即 14 = 2 * 7 。

示例 3：
输入：arr = [3,1,7,11]
输出：false
解释：在该情况下不存在 N 和 M 满足 N = 2 * M 。

提示：
2 <= arr.length <= 500
-10^3 <= arr[i] <= 10^3

 */
public class LeetCode_1346 {
    public boolean checkIfExist(int[] arr) {
        Arrays.sort(arr);
        int one = 0;
        int two = 1;

        while (two < arr.length) {
            if (one == two) {
                two++;
            } else {
                if (arr[one] > 0 && arr[two] > 0) {
                    if (arr[one] > arr[two] / 2) {
                        two++;
                    } else if (arr[one] < arr[two] / 2) {
                        one++;
                    } else {
                        if (arr[two] % 2 == 0) {
                            return true;
                        }
                        two++;
                        one++;
                    }
                } else if (arr[one] < 0 && arr[two] < 0) {
                    if (arr[one] / 2 > arr[two]) {
                        two++;
                    } else if (arr[one] / 2 < arr[two]) {
                        one++;
                    } else {
                        if (arr[one] % 2 == 0) {
                            return true;
                        }
                        two++;
                        one++;
                    }
                } else {
                    if (arr[one] == 0 && arr[two] == 0) {
                        return true;
                    }
                    one++;
                }
            }
        }
        return false;
    }

    @Test
    public void test() {
        System.out.println(checkIfExist(new int[]{-778, -481, 842, 495, 44, 1000, -572, 977, 240, -116,
                673, 997, -958, -539, -964, -187, -701, -928, 472, 965, -672, -88, 443, 36, 388, -127, 115, 704,
                -549, 1000, 998, 291, 633, 423, 57, -77, -543, 72, 328, -938, -192, 382, 179}));
    }
}
