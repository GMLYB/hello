package July;

import java.util.HashMap;
import java.util.Map;

/*
给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
示例1:
输入: pattern = "abba", str = "dog cat cat dog"
输出: true

示例 2:
输入:pattern = "abba", str = "dog cat cat fish"
输出: false

示例 3:
输入: pattern = "aaaa", str = "dog cat cat dog"
输出: false

示例 4:
输入: pattern = "abba", str = "dog dog dog dog"
输出: false
说明:
你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
 */

public class LeetCode_290 {
    public boolean wordPattern(String pattern, String str) {
//        先判断字母和后面的字符串是不是唯一对应
        String[] strs = str.split(" ");
        if (strs.length != pattern.length()) {
            return false;
        }
        Map<Character, String> map1 = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            if (map1.containsKey(pattern.charAt(i))) {
                if (!map1.get(pattern.charAt(i)).equals(strs[i])) {
                    return false;
                }
            } else {
                map1.put(pattern.charAt(i), strs[i]);
            }
        }
        //在判断后面的字符串和前面的字母是不是唯一对应
        Map<String, Character> map2 = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            if (map2.containsKey(strs[i])) {
                if (!map2.get(strs[i]).equals(pattern.charAt(i))) {
                    return false;
                }
            } else {
                map2.put(strs[i], pattern.charAt(i));
            }
        }

        return true;
    }


    public static void main(String[] args) {
        LeetCode_290 code = new LeetCode_290();
        String str1 = "abba";
        String str2 = "dog dog dog dog";
        System.out.println(code.wordPattern(str1, str2));
    }
}
