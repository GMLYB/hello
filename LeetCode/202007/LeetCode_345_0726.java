package July;
/*
编写一个函数，以字符串作为输入，反转该字符串中的元音字母。

示例 1:
输入: "hello"
输出: "holle"

示例 2:
输入: "leetcode"
输出: "leotcede"

说明:
元音字母不包含字母"y"。
 */
public class LeetCode_345 {

    public String reverseVowels(String s) {
        int head = 0;
        int foot = s.length() - 1;
        char[] chars = s.toCharArray();
        boolean headkey = false;
        boolean footkey = false;
        while (head < foot) {
            //是元音字母就停下，不是就前进
            if (chars[head] == 'a' || chars[head] == 'e' || chars[head] == 'i' || chars[head] == 'o' || chars[head] == 'u' || chars[head] == 'A' || chars[head] == 'E' || chars[head] == 'I' || chars[head] == 'O' || chars[head] == 'U'){
                headkey = true;
            }else {
                head++;
            }
            //是元音字母就停下，不是就后退
            if (chars[foot] == 'a' || chars[foot] == 'e' || chars[foot] == 'i' || chars[foot] == 'o' || chars[foot] == 'u' || chars[foot] == 'A' || chars[foot] == 'E' || chars[foot] == 'I' || chars[foot] == 'O' || chars[foot] == 'U'){
                footkey = true;
            }else {
                foot--;
            }
            //两个都是元音字母就交换位置
            if (headkey && footkey){
                char tmp = chars[head];
                chars[head] = chars[foot];
                chars[foot] = tmp;
                head++;
                foot--;
                headkey = false;
                footkey = false;
            }
        }

        return String.valueOf(chars);
    }


    public static void main(String[] args) {
        LeetCode_345 code = new LeetCode_345();
        String str = "leetcode";
        System.out.println(code.reverseVowels(str));
    }
}
