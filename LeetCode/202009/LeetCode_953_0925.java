package September;


import org.junit.Test;


/*

某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。

示例 1：
输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
输出：true
解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。

示例 2：
输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
输出：false
解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。

示例 3：
输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
输出：false
解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，
因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（更多信息）。
 
提示：
1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
在 words[i] 和 order 中的所有字符都是英文小写字母。

 */
public class LeetCode_953 {

    int[] nums = new int[26];

    public boolean isAlienSorted(String[] words, String order) {

        if (words.length == 1) {
            return true;
        }

        int zm = 1;
        for (char c : order.toCharArray()) {
            nums[c - 'a'] = zm++;
        }

        for (int i = 1; i < words.length; i++) {
            if (!pd(words[i - 1], words[i])) {
                return false;
            }
        }
        return true;


    }

    public boolean pd(String s1, String s2) {
        for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
//            System.out.println("s1 = " + nums[s1.charAt(i) - 'a'] + " s2 = " + nums[s2.charAt(i) - 'a']);
            if (nums[s1.charAt(i) - 'a'] < nums[s2.charAt(i) - 'a']) {
                return true;
            } else if (nums[s1.charAt(i) - 'a'] > nums[s2.charAt(i) - 'a']) {
                return false;
            }
        }
        if (s1.length() > s2.length()) {
            return false;
        }

        return true;
    }

    @Test
    public void test() {
        String[] strs1 = {"hello", "leetcode"};
        String order1 = "hlabcdefgijkmnopqrstuvwxyz";
        String[] strs2 = {"word", "world", "row"};
        String order2 = "worldabcefghijkmnpqstuvxyz";
        String[] strs3 = {"apple", "app"};
        String order3 = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(isAlienSorted(strs3, order3));
    }
}
