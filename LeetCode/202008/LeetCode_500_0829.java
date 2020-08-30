package August;

/*
给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。

示例：
输入: ["Hello", "Alaska", "Dad", "Peace"]
输出: ["Alaska", "Dad"]
 
注意：
你可以重复使用键盘上同一字符。
你可以假设输入的字符串将只包含字母。

 */

import java.util.ArrayList;
import java.util.List;

public class LeetCode_500 {

    public static String[] findWords(String[] words) {

        int[] one = new int[128];
        addnum("qwertyuiop",one);
        int[] two = new int[128];
        addnum("asdfghjkl",two);
        int[] three = new int[128];
        addnum("zxcvbnm",three);

        List<String> strlist = new ArrayList<>();
        for (String word : words) {
            String str = word.toLowerCase();
            boolean pd = false;
            if (one[str.charAt(0)] == 1){
                pd = pd(str, one);
            }else if(two[str.charAt(0)] == 1){
                pd = pd(str, two);
            }else if(three[str.charAt(0)] == 1){
                pd = pd(str, three);
            }
            if (pd){
                strlist.add(word);
            }
        }

        return strlist.toArray(new String[strlist.size()]);
    }

    public static boolean pd(String str,int[] nums){
        for (int i = 0; i < str.length(); i++) {
            if (nums[str.charAt(i)] == 0){
                return false;
            }
        }
        return true;
    }


    public static void addnum(String str,int[] nums){
        for (int i = 0; i < str.length(); i++) {
            nums[str.charAt(i)]++;
        }
    }

    public static void main(String[] args) {

        String[] strs = new String[]{"Hello", "Alaska", "Dad", "Peace"};
        String[] words = findWords(strs);
        for (String word : words) {
            System.out.println(word);
        }
    }

}
