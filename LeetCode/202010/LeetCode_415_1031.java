package October;

import org.junit.Test;
/*

给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

提示：
num1 和num2 的长度都小于 5100
num1 和num2 都只包含数字 0-9
num1 和num2 都不包含任何前导零
你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式

 */
public class LeetCode_415 {
    public String addStrings(String num1, String num2) {
        char[] n1 = num1.toCharArray();
        char[] n2 = num2.toCharArray();
        int len = Math.max(n1.length, n2.length);
        StringBuilder sb = new StringBuilder(len + 1);
        int jin =  0;
        for (int i = 0; i < len; i++) {
            int t1 = 0;
            int t2 = 0;
            if (n1.length - i - 1 >= 0) {
                t1 = n1[n1.length - i - 1] - '0';
            }
            if (n2.length - i - 1 >= 0) {
                t2 = n2[n2.length - i - 1] - '0';
            }
            sb.append((t1 + t2 + jin) % 10);
            jin = (t1 + t2 + jin) / 10;
        }
        if (jin != 0){
            sb.append(1);
        }

        return sb.reverse().toString();
    }

    @Test
    public void test() {
        System.out.println(addStrings("99", "9"));
    }
}
