package August;

import java.util.Arrays;

public class LeetCode_389 {
    public char findTheDifference(String s, String t) {
        String str = s + t;
        int c = 0;
        for (int i = 0; i < str.length(); i++) {
            c  = c ^ str.charAt(i);
        }

        return (char) c;
    }

    public char findTheDifference1(String s, String t) {
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);
        int c = 0;
        for (int i = 0; i < s.length(); i++) {
            if (ch1[i] != ch2[i]){
                return ch2[i];
            }
        }

        return ch2[t.length()-1];
    }

    public static void main(String[] args) {
        LeetCode_389 code = new LeetCode_389();
        String s = "abcd";
        String t = "abcde";
        System.out.println(code.findTheDifference(s, t));
        System.out.println(code.findTheDifference1(s, t));
    }
}
