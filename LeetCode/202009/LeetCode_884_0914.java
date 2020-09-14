package September;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
给定两个句子 A 和 B 。 （句子是一串由空格分隔的单词。每个单词仅由小写字母组成。）
如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现，那么这个单词就是不常见的。
返回所有不常用单词的列表。
您可以按任何顺序返回列表。

示例 1：
输入：A = "this apple is sweet", B = "this apple is sour"
输出：["sweet","sour"]

示例 2：
输入：A = "apple apple", B = "banana"
输出：["banana"]
 
提示：
0 <= A.length <= 200
0 <= B.length <= 200
A 和 B 都只包含空格和小写字母。

 */

public class LeetCode_884 {
    public String[] uncommonFromSentences(String A, String B) {

        Map<String, Integer> mapA = new HashMap<>();
        String[] StrA = A.split(" ");
        for (String sA : StrA) {
            if (mapA.containsKey(sA)) {
                mapA.put(sA, mapA.get(sA) + 1);
            } else {
                mapA.put(sA, 1);
            }
        }

        Map<String, Integer> mapB = new HashMap<>();
        String[] StrB = B.split(" ");
        for (String sB : StrB) {
            if (mapB.containsKey(sB)) {
                mapB.put(sB, mapB.get(sB) + 1);
            } else {
                mapB.put(sB, 1);
            }
        }
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : mapA.entrySet()) {
            if (entry.getValue() == 1) {
                if (!mapB.containsKey(entry.getKey())) {
                    list.add(entry.getKey());
                }
            }
            if (mapB.containsKey(entry.getKey())){
                mapB.remove(entry.getKey());
            }

        }
        for (Map.Entry<String, Integer> entry : mapB.entrySet()) {
            if (entry.getValue() == 1) {
                list.add(entry.getKey());
            }
        }

        return list.toArray(new String[list.size()]);
    }

    @Test
    public void test(){
        String A = "this apple is sweet";
        String B = "this apple is sour";
        String[] strings = uncommonFromSentences(A, B);
        for (String string : strings) {
            System.out.println(string);
        }

    }
}
