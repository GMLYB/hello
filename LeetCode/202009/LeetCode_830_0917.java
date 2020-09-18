package September;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
/*

在一个由小写字母构成的字符串 S 中，包含由一些连续的相同字符所构成的分组。
例如，在字符串 S = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
我们称所有包含大于或等于三个连续字符的分组为较大分组。找到每一个较大分组的起始和终止位置。
最终结果按照字典顺序输出。

示例 1:
输入: "abbxxxxzzy"
输出: [[3,6]]
解释: "xxxx" 是一个起始于 3 且终止于 6 的较大分组。

示例 2:
输入: "abc"
输出: []
解释: "a","b" 和 "c" 均不是符合要求的较大分组。

示例 3:
输入: "abcdddeeeeaabbbcd"
输出: [[3,5],[6,9],[12,14]]

 */
public class LeetCode_830 {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> lists = new ArrayList<>();
        int end = 0;
        int lastend = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i-1)){
                end = i-1;
                if ((end - lastend) >= 2){
                    List<Integer> list = new ArrayList<>();
                    list.add(lastend);
                    list.add(end);
                    lists.add(list);
                }
                lastend = end+1;
            }
        }
        //最后一组
        if ((s.length() - 1 - lastend) >= 2){
            List<Integer> list = new ArrayList<>();
            list.add(lastend);
            list.add(s.length() - 1);
            lists.add(list);
        }

        return lists;
    }

    @Test
    public void test(){
        String s1 = "abbxxxxzzy";
        String s2 = "abc";
        String s3 = "abcdddeeeeaabbbcd";
        String s4 = "aaa";
        System.out.println(largeGroupPositions(s3));
    }
}
