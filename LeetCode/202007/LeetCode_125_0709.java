package July;

/*
给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:
输入: "A man, a plan, a canal: Panama"
输出: true

示例 2:
输入: "race a car"
输出: false

 */

public class LeetCode_125 {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) {
            return true;
        }
        String str = s.toUpperCase();
        int i = 0;
        int j = s.length() - 1;
        //两把钥匙
        boolean start = false;
        boolean end = false;
        while (i < j) {
            if ((str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') || ((str.charAt(i) >= '0' && str.charAt(i) <= '9'))) {
//                System.out.println("true i:"+str.charAt(i));
                //拿到钥匙
                start = true;
            }else {
//                System.out.println("false i:"+str.charAt(i));
                i++;
            }
            if ((str.charAt(j) >= 'A' && str.charAt(j) <= 'Z') || ((str.charAt(j) >= '0' && str.charAt(j) <= '9'))) {
//                System.out.println("true j:"+str.charAt(j));
//                拿到钥匙
                end = true;
            }else {
//                System.out.println("false j:"+str.charAt(j));
                j--;
            }
            //两个都有钥匙
            if (start && end) {
                if (str.charAt(i) != str.charAt(j)){
                    return false;
                }
                i++;
                j--;
                //钥匙使用完消失
                start = false;
                end = false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode_125 code125 = new LeetCode_125();
//        String s = "A man, a plan, a canal: Panama";
        String s = "0p";
        System.out.println(code125.isPalindrome(s));

    }
}
