package November;

import java.util.HashMap;
import java.util.Map;

/*

给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
注意：每次拼写（指拼写词汇表中的一个单词）时，chars 中的每个字母都只能用一次。
返回词汇表 words 中你掌握的所有单词的 长度之和。


示例 1：
    输入：words = ["cat","bt","hat","tree"], chars = "atach"
    输出：6
    解释：
    可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。

示例 2：
    输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
    输出：10
    解释：
    可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
     

提示：
    1 <= words.length <= 1000
    1 <= words[i].length, chars.length <= 100
    所有字符串中都仅包含小写英文字母


 */
public class LeetCode_1160 {
    public int countCharacters(String[] words, String chars) {
        int[] zms = new int[26];
        for (char c : chars.toCharArray()) {
            zms[c - 'a']++;
        }
        int sum = 0;
        boolean b = true;
        for (String word : words) {
            int[] tmp = new int[26];
            for (char c : word.toCharArray()) {
                tmp[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                if (tmp[i] != 0 && tmp[i] > zms[i]){
                    b = false;
                    break;
                }
            }
            if (b) {
                System.out.println(word);
                sum += word.length();
            }
            b = true;
        }

        return sum;

    }

    public int countCharacters1(String[] words, String chars) {
        int[] zms = new int[26];
        for (char c : chars.toCharArray()) {
            zms[c - 'a']++;
        }
        int sum = 0;
        boolean b = true;
        for (String word : words) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (char c : word.toCharArray()) {
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    map.put(c, 1);
                }
            }
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue() > zms[entry.getKey()] - 'a') {
                    b = false;
                    break;
                }
            }
            if (b) {
                System.out.println(word);
                sum += word.length();
            }
            b = true;
        }

        return sum;

    }
}
