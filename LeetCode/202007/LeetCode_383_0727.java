package July;

/*
给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，
判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。
如果可以构成，返回 true ；否则返回 false。
(题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)

注意：
你可以假设两个字符串均只含有小写字母。

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true

 */

import java.util.HashMap;
import java.util.Map;

public class LeetCode_383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() == 0 && ransomNote.length() == 0){
            return true;
        }
        if (magazine.length() == 0) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (Character c : ransomNote.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (Character c : magazine.toCharArray()) {
            if (map.containsKey(c)){
                if (map.get(c) == 1){
                    map.remove(c);
                }else {
                    map.put(c,map.get(c)-1);
                }
            }
        }

        if (map.isEmpty()){
            return true;
        }
        return false;

    }

    public static void main(String[] args) {
        LeetCode_383 code = new LeetCode_383();
        String ransom = "aa";
        String magazine = "aab";
        System.out.println(code.canConstruct(ransom, magazine));
    }
}
