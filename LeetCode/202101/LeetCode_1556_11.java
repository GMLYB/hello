package January;

import org.junit.Test;
/*

1556. 千位分隔数
给你一个整数 n，请你每隔三位添加点（即 "." 符号）作为千位分隔符，并将结果以字符串格式返回。



示例 1：

输入：n = 987
输出："987"
示例 2：

输入：n = 1234
输出："1.234"
示例 3：

输入：n = 123456789
输出："123.456.789"
示例 4：

输入：n = 0
输出："0"


提示：
0 <= n < 2^31

 */
public class LeetCode_1556 {
    public String thousandSeparator(int n) {
        // 转换为字符串
        String str = String.valueOf(n);
        //进行翻转
        StringBuilder sb = new StringBuilder(str);
        //进行结果存储
        StringBuilder res = new StringBuilder(str.length());
        sb = sb.reverse();
        // 先转换为字符串
        String tmp = sb.toString();
        //3个3个加到 res 中
        for (int i = 0; i < sb.length() / 3; i++) {
            res.append(tmp.substring(i * 3, (i + 1) * 3)).append('.');
        }
        if (sb.length() % 3 != 0) {
            return res.append(tmp.substring((sb.length() / 3) * 3)).reverse().toString();
        }
        String s = res.reverse().toString();
        if (s.charAt(0) == '.') {
            s = s.substring(1);
            return s;
        }

        return res.reverse().toString();

    }

    @Test
    public void test() {
        System.out.println(thousandSeparator(9871));
    }
}
