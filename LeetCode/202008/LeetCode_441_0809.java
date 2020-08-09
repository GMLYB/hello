package August;

/*
你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
给定一个数字 n，找出可形成完整阶梯行的总行数。
n 是一个非负整数，并且在32位有符号整型的范围内。

示例 1:
n = 5
硬币可排列成以下几行:
¤
¤ ¤
¤ ¤
因为第三行不完整，所以返回2.

示例 2:
n = 8
硬币可排列成以下几行:
¤
¤ ¤
¤ ¤ ¤
¤ ¤
因为第四行不完整，所以返回3.

 */

/*
    n = (s + w) * x / 2
    5 = (1 + w) * w / 2
   2n = (1+w)w
   w^2 + w + 1/4= 2n + 0.25
   (w + 0.5)^2 = (2n+0.25)
   w = sqrt(2n+0.25) - 0.5
 */

public class LeetCode_441 {
    public int arrangeCoins(int n) {
        // 1804289383 输入 n 太大，所以转成long型
        long ln = 2 * (long)n;
        return (int) Math.floor(Math.sqrt(ln+0.25)-0.5);
//        return (int) Math.floor(Math.sqrt(2*(long)n+0.25)-0.5);
    }

    public static void main(String[] args) {
        LeetCode_441 code = new LeetCode_441();
        int i = code.arrangeCoins(1804289383);
        System.out.println(i);
    }
}
