package September;

import org.junit.Test;
/*

给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。

示例 1:
输入: [[1,1,0],[1,0,1],[0,0,0]]
输出: [[1,0,0],[0,1,0],[1,1,1]]
解释: 首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
     然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]

示例 2:
输入: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
输出: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
解释: 首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
     然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]

说明:
1 <= A.length = A[0].length <= 20
0 <= A[i][j] <= 1

 */
public class LeetCode_832 {

    public int[][] flipAndInvertImage(int[][] A) {
        // 1 ^ 1 =0   1^ 0 = 1
        for (int i = 0; i < A.length; i++) {
            //length + 1 为了解决中间的那个数字
            for (int j = 0; j < (A[i].length + 1) / 2; j++) {
                int tmp = A[i][j];
                A[i][j] = A[i][A[i].length - 1 - j] ^ 1;
                A[i][A[i].length - 1 - j] = tmp ^ 1;
            }
        }
        return A;

    }

    public int[][] flipAndInvertImage1(int[][] A) {
        //交换器
        int[] swap = {1, 0};
        for (int i = 0; i < A.length; i++) {
            //length + 1 为了解决中间的那个数字
            for (int j = 0; j < (A[i].length + 1) / 2; j++) {
                int tmp = A[i][j];
                A[i][j] = swap[A[i][A[i].length - 1 - j]];
                A[i][A[i].length - 1 - j] = swap[tmp];
            }
        }
        return A;

    }

    @Test
    public void test() {
        int[][] A = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        int[][] ints = flipAndInvertImage(A);
        for (int[] anInt : ints) {
            for (int i : anInt) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
