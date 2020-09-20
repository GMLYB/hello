package September;
/*

给定两个由小写字母构成的字符串 A 和 B ，
只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。

示例 1：
输入： A = "ab", B = "ba"
输出： true

示例 2：
输入： A = "ab", B = "ab"
输出： false

示例 3:
输入： A = "aa", B = "aa"
输出： true

示例 4：
输入： A = "aaaaaaabc", B = "aaaaaaacb"
输出： true

示例 5：
输入： A = "", B = "aa"
输出： false
 
提示：
0 <= A.length <= 20000
0 <= B.length <= 20000
A 和 B 仅由小写字母构成。

 */
public class LeetCode_859 {
    public boolean buddyStrings(String A, String B) {

        if (A.length() != B.length()) {
            return false;
        }

        //用来判断是否存在重复字母
        char[] zm = new char[26];
        boolean pd = false;
        //统计出现不同字母的数量 chars进行保存
        int count = 0;
        char[] chars = new char[4];
        int index = 0;
        for (int i = 0; i < A.length(); i++) {
            //不同就存起来
            if (A.charAt(i) != B.charAt(i)) {
                chars[index++] = A.charAt(i);
                chars[index++] = B.charAt(i);
                count++;
            } else {
                // 相同就统计是否重复
                zm[A.charAt(i)-'a']++;
                if (zm[A.charAt(i)-'a'] > 1) {
                    pd = true;
                }
            }
            //不同之处大于2就返回false
            if (count > 2) {
                return false;
            }
        }
        //不同之处为2 或者 相等但是存在重复字母      交换之后相等
        if ((count == 2 || (count == 0 && pd)) && chars[0] == chars[3] && chars[1] == chars[2]) {
            return true;
        }

        return false;

    }
}
