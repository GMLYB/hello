package August;

import java.util.Arrays;

/*
实现一个算法，确定一个字符串 s 的所有字符是否全都不同。

示例 1：
输入: s = "leetcode"
输出: false

示例 2：
输入: s = "abc"
输出: true

限制：
0 <= len(s) <= 100
如果你不使用额外的数据结构，会很加分。
 */

public class LeetCode_01_01 {
    public boolean isUnique(String astr) {

        if (astr.length()==0&&astr.length()==1){
            return true;
        }

        char[] strs = astr.toCharArray();
        Arrays.sort(strs);
        for (int i = 1; i < strs.length; i++) {
            if (strs[i] == strs[i-1]){
                return false;
            }
        }
        return true;
    }
}
