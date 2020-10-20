package October;

import org.junit.Test;
/*
给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
在 S 上反复执行重复项删除操作，直到无法继续删除。
在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。

示例：
输入："abbaca"
输出："ca"
解释：
例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。
之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 

提示：
1 <= S.length <= 20000
S 仅由小写英文字母组成。

可以使用 栈 进行计算

 */
public class LeetCode_1047 {
    public String removeDuplicates(String S) {
        //判断 是否出现删除 的S
        boolean b = true;
        //判断是否重复添加最后一个字符
        boolean ad = true;
        StringBuilder sb = new StringBuilder();
        while (b) {
            b = false;
            System.out.println("S.length = "+S.length());
            for (int i = 1; i < S.length(); i++) {
                //相同就直接跳过那两个
                if (S.charAt(i - 1) == S.charAt(i)) {
                    i++;
                    b = true;
                    System.out.println(i);
                    //加上最后一个
                    if (i == (S.length() - 1)){
                        sb.append(S.charAt(i));
                        ad = false;
                    }
                } else {
                    //不相同就先加上
                    sb.append(S.charAt(i - 1));
                }

            }
            //长度大于2 且最后一个不等于倒数第二个
            if (S.length() >= 2 && S.charAt(S.length() - 1) != S.charAt(S.length() - 2)) {
                //且没有重复添加最后一个，就加上最后一个字符
                if (ad){
                    sb.append(S.charAt(S.length() - 1));
                }
            }
            //初始化判断状态
            S = sb.toString();
            sb = new StringBuilder();
            ad = true;
            //长度小于等于1的时候，就直接返回了
            if (S.length() <= 1){
                return S;
            }
        }

        return S;

    }

    @Test
    public void test() {

        String str1 = "aaaaaaaaa";

        System.out.println(removeDuplicates(str1));


    }
}
