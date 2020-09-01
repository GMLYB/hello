package August;

import java.lang.reflect.Method;
/*

给定两个字符串, A 和 B。
A 的旋转操作就是将 A 最左边的字符移动到最右边。 
例如, 若 A = 'abcde'，在移动一次之后结果就是'bcdea' 。如果在若干次旋转操作之后，A 能变成B，那么返回True。
示例 1:
输入: A = 'abcde', B = 'cdeab'
输出: true

示例 2:
输入: A = 'abcde', B = 'abced'
输出: false

注意：
A 和 B 长度不超过 100。

 */
public class LeetCode_796 {

    public boolean rotateString(String A, String B) {

        if (A.length() != B.length()) {
            return false;
        }
        if (A.equals(B)){
            return true;
        }
        String A2 = A + A;
        for (int i = 0; i < A.length(); i++) {
            if (B.equals(A2.substring(0 + i, A.length() + i))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        Method method = LeetCode_796.class.getDeclaredMethod("rotateString", String.class, String.class);
        Object res = method.invoke(LeetCode_796.class.newInstance(), "abcde", "abced");
        System.out.println(res);

    }
}
