package September;

import org.junit.Test;

/*

给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
如果剩余字符少于 k 个，则将剩余字符全部反转。
如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 
示例:
输入: s = "abcdefg", k = 2
输出: "bacdfeg"

提示：
该字符串只包含小写英文字母。
给定字符串的长度和 k 在 [1, 10000] 范围内。

 */

public class LeetCode_541 {

    public String reverseStr(String s, int k) {
        int count = 0;
        if (s.length() % k == 0){
            count = s.length() / k;
        }else {
            count = s.length() / k + 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                StringBuilder tmp = new StringBuilder();
                String substring = s.substring(i * k, (i * k) + (k < (s.length() - i * k) ? k : (s.length() - i * k)));
                tmp.append(substring);
                sb.append(tmp.reverse());
            } else {
                sb.append(s.substring(i * k, (i * k) + (k < (s.length() - i * k) ? k : (s.length() - i * k))));
            }
        }
        return sb.toString();
    }

    @Test
    public void test() {
        String s1 = "abcdefg";
        int k1 = 2;
//        System.out.println(reverseStr(s1, k1));
        String s2 = "abcd";
        int k2 = 3;
        System.out.println(reverseStr(s2, k2));
    }


}
