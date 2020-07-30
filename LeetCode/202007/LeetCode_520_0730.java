package July;
/*

    给定一个单词，你需要判断单词的大写使用是否正确。
    我们定义，在以下情况时，单词的大写用法是正确的：
    全部字母都是大写，比如"USA"。
    单词中所有字母都不是大写，比如"leetcode"。
    如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
    否则，我们定义这个单词没有正确使用大写字母。

    示例 1:
    输入: "USA"
    输出: True

    示例 2:
    输入: "FlaG"
    输出: False
    注意: 输入是由大写和小写拉丁字母组成的非空单词。

 */
public class LeetCode_520 {
    public boolean detectCapitalUse(String word) {
        //得到第一个字母
        char firstchar = word.charAt(0);
        //小写的情况下，全部都是小写
        if (firstchar >= 'a' && firstchar <= 'z') {
            String lowerstr = word.toLowerCase();
            if (word.equals(lowerstr)){
                return true;
            }else {
                return false;
            }
        }else {
            //大写的情况，分成两种，第一种，全部大写；第二种，首字母大写
            String upperstr = word.toUpperCase();
            if (word.equals(upperstr)){
                return true;
            }

            String substr = word.substring(1);
            String lowsubstr = substr.toLowerCase();

            if (substr.equals(lowsubstr)){
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        LeetCode_520 code = new LeetCode_520();
//        String str = "leetcode";
        String str = "FlaG";
        System.out.println(code.detectCapitalUse(str));

    }
}
