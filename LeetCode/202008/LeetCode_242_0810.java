package August;

import java.util.Arrays;

/*
给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

示例 1:
输入: s = "anagram", t = "nagaram"
输出: true

示例 2:
输入: s = "rat", t = "car"
输出: false

说明:
你可以假设字符串只包含小写字母。
进阶:
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？

 */


public class LeetCode_242 {

    // 4ms 40MB
    public boolean isAnagram(String s, String t){
        if (s.length() != t.length()){
            return false;
        }
        int[] sl = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sl[s.charAt(i)-97]++;
        }
        int[] tl = new int[26];
        for (int i = 0; i < t.length(); i++) {
            tl[t.charAt(i)-97]++;
        }

        for (int i = 0; i < 26; i++) {
            if (sl[i] != tl[i]){
                return false;
            }
        }
        return true;
    }

    //2ms 40MB
    public boolean isAnagram4(String s, String t){
        if (s.length() != t.length()){
            return false;
        }
        char[] cs = s.toCharArray();
        char[] ts = t.toCharArray();
        Arrays.sort(cs);
        Arrays.sort(ts);
        if (String.valueOf(cs).equals(String.valueOf(ts))){
            return true;
        }
        return false;
    }

    //accept 3ms 39.5MB
    public boolean isAnagram3(String s, String t) {
        //长度不相等
        if (s.length() != t.length()){
            return false;
        }
        //长度为奇数的时候 采取 异或的方式
        if (s.length()%2 != 0){
            String str = s + t;
            int c = 0;
            for (int i = 0; i < str.length(); i++) {
                c = c ^ str.charAt(i);
            }
            if (c == 0){
                return true;
            }
            return false;
        }else {
            //长度为偶数的时候，采取排序比较的形式
            char[] cs = s.toCharArray();
            char[] ts = t.toCharArray();
            Arrays.sort(cs);
            Arrays.sort(ts);

//            for (int i = 0; i < s.length(); i++) {
//                if (cs[i] != ts[i]){
//                    return false;
//                }
//            }
//            return true;
            //转成字符串直接比较
            if (String.valueOf(cs).equals(String.valueOf(ts))){
                return true;
            }
            return false;

        }

    }

    // accept 4ms 40MB
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }

        char[] cs = s.toCharArray();
        char[] ts = t.toCharArray();
        Arrays.sort(cs);
        Arrays.sort(ts);
        for (int i = 0; i < s.length(); i++) {
            if (cs[i] != ts[i]){
                return false;
            }
        }
        return true;
    }


    /*
        "aa"
        "bb"
     */
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }

        String str = s + t;

        int c = 0;
        for (int i = 0; i < str.length(); i++) {
            c = c ^ str.charAt(i);
        }
        if (c == 0){
            return true;
        }

        return false;

    }

    public static void main(String[] args) {
        LeetCode_242 code = new LeetCode_242();
        String s = "anagram";
        String t = "nagaram";
        System.out.println(code.isAnagram(s,t));
    }
}
