package October;

import java.util.ArrayList;
import java.util.List;
/*


给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
你可以按任意顺序返回答案。



示例 1：

输入：["bella","label","roller"]
输出：["e","l","l"]
示例 2：

输入：["cool","lock","cook"]
输出：["c","o"]

 */
public class LeetCode_1002 {
    public List<String> commonChars(String[] A) {
        List<String> list = new ArrayList<>();
        int[][] zm = new int[A.length][28];
        for (int i = 0; i < A.length; i++) {
            for (char c : A[i].toCharArray()) {
                zm[i][c-'a']++;
            }
        }

        for (int i = 0; i < 28; i++) {
            int min = 101;
            for (int j = 0; j < A.length; j++) {
                min = Math.min(min,zm[j][i]);
            }
            char c = (char)(i+'a');
            for (int j = 0; j < min; j++) {
                list.add(String.valueOf(c));
            }
        }
        return list;

    }
}
