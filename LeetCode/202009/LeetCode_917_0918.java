package September;

import org.junit.Test;

/**
 * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
 *
 * 示例 1：
 * 输入："ab-cd"
 * 输出："dc-ba"
 *
 * 示例 2：
 * 输入："a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 *
 * 示例 3：
 * 输入："Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 *
 * 提示：
 * S.length <= 100
 * 33 <= S[i].ASCIIcode <= 122 
 * S 中不包含 \ or "
 *
 */
public class LeetCode_917 {

    public String reverseOnlyLetters(String S) {

        int start = 0;
        int end = S.length() - 1;

        char[] chars = S.toCharArray();

        while (start < end) {
            //不是字母就往前移动
            while (start < S.length() && !pd(chars[start])) {
                start++;
            }
            //不是字母就往后移动
            while (end >= 0 &&!pd(chars[end])) {
                end--;
            }
            //超出边界值 或者 头位置 大于 尾位置 返回
            if ( start >= S.length() || end < 0 || start >= end){
                return String.valueOf(chars);
            }
            // 交换头 尾
            char tmp = S.charAt(start);
            chars[start] = chars[end];
            chars[end] = tmp;
            start++;
            end--;

        }

        return String.valueOf(chars);
    }

    public boolean pd(char c) {
        if ((c >= 'a' && c <= 'z') || c >= 'A' && c <= 'Z') {
            return true;
        }
        return false;
    }


    @Test
    public void test() {
        String s1 = "7_28]";
        String s2 = "ab-cd";
        String s3 = "a-bC-dEf-ghIj";
        String s4 = "?6C40E";
        System.out.println(reverseOnlyLetters(s4));
    }

}
