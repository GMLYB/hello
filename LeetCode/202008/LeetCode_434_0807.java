package August;

/*
统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
请注意，你可以假定字符串里不包括任何不可打印的字符。

示例:
输入: "Hello, my name is John"
输出: 5
解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。

 */

public class LeetCode_434 {

    public int countSegments(String s) {
        String[] strs = s.split(" ");
        int count = strs.length;
        for (String st : strs){
            if ("".equals(st)){
                count--;
            }
        }
        return count;
    }

    public int countSegments2(String s) {
        //去掉前后的空格，然后和空字符比较 为空就返回0
        if ("".equals(s.trim())) return 0;
        //正则表达式
        // \s 	 匹配任何不可见字符，包括空格、制表符、换页符等等。等价于[ \f\n\r\t\v]。
        // + 匹配前面的子表达式一次或多次(大于等于1次）。例如，“zo+”能匹配“zo”以及“zoo”，但不能匹配“z”。+等价于{1,}。
        return s.trim().split("\\s+").length;
    }

    public static void main(String[] args) {

    }
}
