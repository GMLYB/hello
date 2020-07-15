package July;
/*

给定两个字符串 s 和 t，判断它们是否是同构的。
如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。

示例 1:
输入: s = "egg", t = "add"
输出: true

示例 2:
输入: s = "foo", t = "bar"
输出: false

示例 3:
输入: s = "paper", t = "title"
输出: true

说明:
你可以假设 s 和 t 具有相同的长度。

 */
import java.util.HashMap;
import java.util.Map;

public class LeetCode_205 {
    public boolean isIsomorphic(String s, String t) {
        Map<Character,Character> map1 = new HashMap<>();
        Map<Character,Character> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map1.containsKey(t.charAt(i))){
                if (map1.get(t.charAt(i)) != s.charAt(i)){
                    return false;
                }
            }else {
                map1.put(t.charAt(i),s.charAt(i));
            }

            if (map2.containsKey(s.charAt(i))){
                if (map2.get(s.charAt(i)) != t.charAt(i)){
                    return false;
                }
            }else {
                map2.put(s.charAt(i),t.charAt(i));
            }
        }
        return true;
    }


    public static void main(String[] args) {
        LeetCode_205 code = new LeetCode_205();
        String s = "ab";
        String t = "ca";
        System.out.println(code.isIsomorphic(s, t));
    }
}
