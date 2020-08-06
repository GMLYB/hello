package August;




/*
给定一个字符串来代表一个学生的出勤记录，这个记录仅包含以下三个字符：
'A' : Absent，缺勤
'L' : Late，迟到
'P' : Present，到场
如果一个学生的出勤记录中不超过一个'A'(缺勤)并且不超过两个连续的'L'(迟到),那么这个学生会被奖赏。
你需要根据这个学生的出勤记录判断他是否会被奖赏。

示例 1:
输入: "PPALLP"
输出: True

示例 2:
输入: "PPALLL"
输出: False
 */

import java.lang.reflect.Method;

public class LeetCode_551 {

    public boolean checkRecord(String s) {
        int countA = 0;
        int countL = 0;

        if (s.charAt(0) == 'A') {
            countA++;
        } else if (s.charAt(0) == 'L') {
            countL++;
        }

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                countA++;
                if (countA > 1) {
                    return false;
                }
            }
            if (s.charAt(i) == 'L') {
                countL++;
                if (countL > 2) {
                    return false;
                }
            } else {
                countL = 0;
            }
        }
        return true;
    }

    public static void main(String[] args) {

//        String str = "PPALLP";
        String str = "PPALLLP";
        try {
            Method checkRecord = LeetCode_551.class.getDeclaredMethod("checkRecord", String.class);
            System.out.println(checkRecord.invoke(new LeetCode_551(), str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
