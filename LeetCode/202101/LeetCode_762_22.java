package January;

import org.junit.Test;

/*
762. 二进制表示中质数个计算置位
给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。

（注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。还有，1 不是质数。）

示例 1:

输入: L = 6, R = 10
输出: 4
解释:
6 -> 110 (2 个计算置位，2 是质数)
7 -> 111 (3 个计算置位，3 是质数)
9 -> 1001 (2 个计算置位，2 是质数)
10-> 1010 (2 个计算置位，2 是质数)
示例 2:

输入: L = 10, R = 15
输出: 5
解释:
10 -> 1010 (2 个计算置位, 2 是质数)
11 -> 1011 (3 个计算置位, 3 是质数)
12 -> 1100 (2 个计算置位, 2 是质数)
13 -> 1101 (3 个计算置位, 3 是质数)
14 -> 1110 (3 个计算置位, 3 是质数)
15 -> 1111 (4 个计算置位, 4 不是质数)
注意:

L, R 是 L <= R 且在 [1, 10^6] 中的整数。
R - L 的最大值为 10000。
 */
public class LeetCode_762 {
    public int countPrimeSetBits(int L, int R) {

        int count = 0;

        for (int i = L; i <= R; i++) {
            int weiShu = WeiShu(i);
            if (isZhiShu(weiShu)) {
                count++;
            }
        }

        return count;

    }

    /**
     * 判断是否为质数
     *
     * @param num
     * @return true为质数 false不是
     */
    public boolean isZhiShu(int num) {

        if (num == 1) {
            return false;
        }

        //能整除的话就不是质数 2 ~ (num-1)
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    //范围在1 - 1000000，2 的 20次方 = 2^20 = 1 048 576 > 1000000
    public boolean isZhiShu2(int num) {

        if (num == 1) {
            return false;
        }

        //2 3 5 7 11 13 17 19
        if (num == 2 || num == 3 || num == 5 || num == 7 || num == 11 || num == 13 || num == 17 || num == 19) {
            return true;
        }
        return false;
    }

    /**
     * 先转换为二进制数字，计算置位数之和
     *
     * @param num
     * @return
     */
    public int WeiShu(int num) {

        int sum = 0;

        while (num != 0) {
            if (num % 2 != 0) {
                sum++;
            }
            num = num / 2;
        }

        return sum;

    }

    @Test
    public void test() {
        System.out.println(countPrimeSetBits(842, 888));
    }
}
