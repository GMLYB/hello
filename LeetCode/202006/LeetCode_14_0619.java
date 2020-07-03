package day6_19;

public class LeetCode_14 {

    /*
     编写一个函数来查找字符串数组中的最长公共前缀。
     如果不存在公共前缀，返回空字符串 ""。
     示例 1:
     输入: ["flower","flow","flight"]
     输出: "fl"

     示例 2:
     输入: ["dog","racecar","car"]
     输出: ""
     解释: 输入不存在公共前缀。
     说明:
     所有输入只包含小写字母 a-z 。
     */

    public String longestCommonPrefix(String[] strs) {

        if(strs.length > 0){
            int min = 10000;

            //找出最小的字符串长度
            for (String str : strs) {
                if(min > str.length()){
                    min = str.length();
                }
            }
            StringBuilder sb = new StringBuilder(min);
            boolean pd = true;
            for (int i = 0; i < min; i++) {
                for (int j = 1; j < strs.length; j++) {
                    if(strs[0].charAt(i) != strs[j].charAt(i)){
                        pd = false;
                        break;
                    }
                }
                if(pd){
                    sb.append(strs[0].charAt(i));
                }else {
                    break;
                }
            }
            return sb.toString();
        }
        return "";
    }

    public static void main(String[] args) {
        LeetCode_14 code14 = new LeetCode_14();
        String [] strs = {};
        String str = code14.longestCommonPrefix(strs);

        System.out.println(str);

    }


}
