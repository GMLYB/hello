package October;

import org.junit.Test;
/*

给定一个正整数 N，找到并返回 N 的二进制表示中两个相邻 1 之间的最长距离。 
如果没有两个相邻的 1，返回 0 。

示例 1：
输入：22
输出：2
解释：
22 的二进制是 0b10110 。
在 22 的二进制表示中，有三个 1，组成两对相邻的 1 。
第一对相邻的 1 中，两个 1 之间的距离为 2 。
第二对相邻的 1 中，两个 1 之间的距离为 1 。
答案取两个距离之中最大的，也就是 2 。

示例 2：
输入：5
输出：2
解释：
5 的二进制是 0b101 。

示例 3：
输入：6
输出：1
解释：
6 的二进制是 0b110 。

示例 4：
输入：8
输出：0
解释：
8 的二进制是 0b1000 。
在 8 的二进制表示中没有相邻的两个 1，所以返回 0 。
 
提示：
1 <= N <= 10^9

 */
public class LeetCode_868 {
    public int binaryGap(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0){
            if (n % 2 == 0){
                sb.append(0);
            }else {
                sb.append(1);
            }
            n = n / 2;
        }
        String two = sb.reverse().toString();
        int index = 0;
        int len = 0;
        for (int i = 0; i < two.length(); i++) {
            if (two.charAt(i) == '1'){
                len = Math.max(len,i - index);
                index = i;
            }
        }

        return len;
    }

    @Test
    public void test(){
        System.out.println(binaryGap(22));
        System.out.println(binaryGap(5));
        System.out.println(binaryGap(6));
        System.out.println(binaryGap(8));
    }


}
