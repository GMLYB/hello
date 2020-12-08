package December;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
/*

1309. 解码字母到整数映射
给你一个字符串 s，它由数字（'0' - '9'）和 '#' 组成。我们希望按下述规则将 s 映射为一些小写英文字符：
字符（'a' - 'i'）分别用（'1' - '9'）表示。
字符（'j' - 'z'）分别用（'10#' - '26#'）表示。
返回映射之后形成的新字符串。
题目数据保证映射始终唯一。


示例 1：
输入：s = "10#11#12"
输出："jkab"
解释："j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".

示例 2：
输入：s = "1326#"
输出："acz"

示例 3：
输入：s = "25#"
输出："y"

示例 4：
输入：s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
输出："abcdefghijklmnopqrstuvwxyz"

提示：
1 <= s.length <= 1000
s[i] 只包含数字（'0'-'9'）和 '#' 字符。
s 是映射始终存在的有效字符串。

 */
public class LeetCode_1309 {
    public String freqAlphabets(String s) {
        Map<String, Character> map = new HashMap<>();
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g',
                'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u',
                'v', 'w', 'x', 'y', 'z'};
        for (int i = 1; i <= 26; i++) {
            if (i <= 9) {
                map.put(String.valueOf(i), chars[i - 1]);
            } else {
                map.put(String.valueOf(i) + "#", chars[i - 1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i + 2 < s.length() && s.charAt(i + 2) == '#') {
                sb.append(map.get(s.substring(i, i + 3)));
                i += 2;
            } else {
                sb.append(map.get(String.valueOf(s.charAt(i))));
            }
        }
        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(freqAlphabets("10#11#12"));
        System.out.println(freqAlphabets("1326#"));
        System.out.println(freqAlphabets("25#"));
        System.out.println(freqAlphabets("12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"));
    }
}
