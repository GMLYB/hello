package November;

import org.junit.Test;
/*

给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
输入为三个整数：day、month 和 year，分别表示日、月、年。
您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。

示例 1：
输入：day = 31, month = 8, year = 2019
输出："Saturday"

示例 2：
输入：day = 18, month = 7, year = 1999
输出："Sunday"

示例 3：
输入：day = 15, month = 8, year = 1993
输出："Sunday"
 
提示：
给出的日期一定是在 1971 到 2100 年之间的有效日期。


 */
public class LeetCode_1185 {


    public String dayOfTheWeek(int day, int month, int year) {
        int days = 0;
        for (int i = 1971; i < year; i++) {
            if ((i % 100 != 0 && i % 4 == 0) || i % 400 == 0) {
                days += 366;
            } else {
                days += 365;
            }
        }
        System.out.println(days);
        for (int i = 1; i < month; i++) {
            if (i == 2) {
                if ((year % 100 != 0 && year % 4 == 0) || year % 400 == 0) {
                    days += 29;
                } else {
                    days += 28;
                }
            } else if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                days += 31;
            } else {
                days += 30;
            }
        }
        System.out.println(days);
        days += day;
        System.out.println(days);
        String[] week = new String[]{"Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        return week[(days - 1) % 7];
    }

    @Test
    public void test() {
        dayOfTheWeek(31, 8, 2019);
    }
}
