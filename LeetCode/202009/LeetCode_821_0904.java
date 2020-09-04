package September;

import org.junit.Test;
/*
给定一个字符串 S 和一个字符 C。返回一个代表字符串 S 中每个字符到字符串 S 中的字符 C 的最短距离的数组。

示例 1:

输入: S = "loveleetcode", C = 'e'
输出: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
说明:

字符串 S 的长度范围为 [1, 10000]。
C 是一个单字符，且保证是字符串 S 里的字符。
S 和 C 中的所有字母均为小写字母。

 */
public class LeetCode_821 {
    public int[] shortestToChar(String S, char C) {

        String[] strs = S.split(String.valueOf(C));

        int[] nums = new int[S.length()];
        int i = 0;
        for (String str : strs) {
//            System.out.println(str+"-");
            if (str.equals("")) {
                nums[i] = 0;
                i++;
            } else {
                //假设头尾都是指定的字符，移动一个字母，距离+1
                for (int j = 0; j < (str.length() + 1) / 2; j++) {
                    nums[i + j] = j + 1;
                    nums[i + str.length() - j - 1] = j + 1;
                }
                i += str.length() + 1;
            }
        }
        //若开头不是指定的字符，修正开头的距离
        if (S.charAt(0) != C) {
            for (int j = 0; j < strs[0].length(); j++) {
                nums[j] = strs[0].length() - j;
            }
        }

        //若结尾不是指定的字符，修正结尾的距离
        if (S.charAt(S.length()-1) != C) {
            for (int j = 0; j < strs[strs.length-1].length(); j++) {
                nums[nums.length-1-j] = strs[strs.length-1].length() - j;
            }
        }

        return nums;
    }

    @Test
    public void test() {
        String S1 = "loveleetcode";
        String S2 = "aaab";
        String S3 = "abaa";
        char C1 = 'e';
        char C2 = 'b';
        char C3 = 'b';
        int[] ints = shortestToChar(S3, C3);
        for (int i : ints) {
            System.out.print(i + " ");
        }
    }
}
