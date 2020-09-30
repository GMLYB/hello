package September;

import org.junit.Test;
/*

给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
注意:
假设字符串的长度不会超过 1010。

示例 1:
输入:
"abccccdd"
输出:
7
解释:
我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。

 */
public class LeetCode_409 {

    public int longestPalindrome(String s) {
        int[] zmcount = new int[128];
        for (char c : s.toCharArray()) {
            zmcount[c]++;
        }
        int sum = 0;
        boolean pd = true;
        for (int i = 64; i < 128; i++) {
            if (zmcount[i] % 2 == 0) {
                sum += zmcount[i];
            } else {
                pd = false;
                if (zmcount[i] > 1) {
                    sum += zmcount[i] - 1;
                }
            }
        }
        if (pd) {
            return sum;
        }
        return sum + 1;

    }


    public int longestPalindrome1(String s) {

        StringBuilder sb = new StringBuilder();
        sb.append(s);
        StringBuilder reverse = sb.reverse();
        if (reverse.toString().equals(s)) {
            return s.length();
        }

        return Math.max(longestPalindrome1(s.substring(1)), longestPalindrome1(s.substring(0, s.length() - 1)));
    }

    @Test
    public void test() {
        String s = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanl" +
                "ongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionoft" +
                "hatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveIti" +
                "saltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotcon" +
                "secratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditf" +
                "araboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebu" +
                "titcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedwork" +
                "whichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafs" +
                "kremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichth" +
                "eygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedin" +
                "vainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeop" +
                "leforthepeopleshallnotperishfromtheearth";
        System.out.println(longestPalindrome(s));
    }
}
