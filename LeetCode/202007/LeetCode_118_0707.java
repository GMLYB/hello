package July;

import java.util.ArrayList;
import java.util.List;

/*

118. 杨辉三角
给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
在杨辉三角中，每个数是它左上方和右上方的数的和。

示例:
输入: 5
输出:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
 */

public class LeetCode_118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        int[][] nei = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            List<Integer> tmplist = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i){
                    nei[i][j] = 1;
                }else {
                    nei[i][j] = nei[i-1][j-1] + nei[i-1][j];
                }
                tmplist.add(nei[i][j]);
            }
            list.add(tmplist);
        }
        return list;

    }

    public static void main(String[] args) {
        LeetCode_118 code118 = new LeetCode_118();
        List<List<Integer>> lists = code118.generate(5);
        System.out.println(lists);
    }
}
