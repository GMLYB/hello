package November;
/*
给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

示例：
输入："Let's take LeetCode contest"
输出："s'teL ekat edoCteeL tsetnoc"

提示：
在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。

 */
public class LeetCode_557 {
    public String reverseWords(String s) {
        String[] strings = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            StringBuilder tsb = new StringBuilder(string);
            sb.append(tsb.reverse().toString()).append(" ");
        }

        return sb.toString().substring(0, sb.toString().length() - 1);
    }
}
