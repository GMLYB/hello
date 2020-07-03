package day6_29;

/*
    给你两个二进制字符串，返回它们的和（用二进制表示）。
    输入为 非空 字符串且只包含数字 1 和 0。

    示例 1:
    输入: a = "11", b = "1"
    输出: "100"

    示例 2:
    输入: a = "1010", b = "1011"
    输出: "10101"

    提示：
    每个字符串仅由字符 '0' 或 '1' 组成。
    1 <= a.length, b.length <= 10^4
    字符串如果不是 "0" ，就都不含前导零。

 */

public class LeetCode_67 {
    public String addBinary(String a, String b) {
        //先分出长短
        String maxstr = a.length() > b.length() ? a : b;
        String minstr = a.length() > b.length() ? b : a;

        //短的长度为0，直接返回长的字符串
        if (minstr.length() == 0) {
            return maxstr;
        }
        //判断是否进位，两个字符串相同且等于1，就进位
        boolean islike = false;

        //StringBuilder提升速度，方便反转
        StringBuilder sb = new StringBuilder((maxstr.length() + 1));

        for (int i = 0; i < maxstr.length(); i++) {
            //在短的字符串长度之内，要进行比较；不在短的字符串长度之内，就只操作长的字符串
            if (i < minstr.length()) {
                //两个字符串相等的情况：1 和 1 ； 0 和 0
                if (maxstr.charAt(maxstr.length() - 1 - i) == minstr.charAt(minstr.length() - 1 - i)) {
                    //有进位情况，1+1+1=1 ；0+0+1=1，所以添加一个1
                    if (islike) {
                        sb.append('1');
                    } else {
                        sb.append('0');
                    }
                    //两个都是1，就把进位给true，下个数字进行 +1 计算
                    if (maxstr.charAt(maxstr.length() - 1 - i) == '1') {
                        islike = true;
                    } else {
                        islike = false;
                    }
                } else {//这就是两个不相等情况：1和0 ；0和1
                    //有进位，0 + 1 + 1 = 0；
                    if (islike) {
                        sb.append('0');
                        islike = true;
                    } else {
                        sb.append('1');
                        islike = false;
                    }
                }
            } else {//短的计算完了，只剩下长的字符串
                //又等于1，又有进位的情况， 1+1 = 0 ，进位给true
                if (maxstr.charAt(maxstr.length() - 1 - i) == '1' && islike == true) {
                    sb.append('0');
                    islike = true;
                } else if (maxstr.charAt(maxstr.length() - 1 - i) == '1' || islike == true) {
                    sb.append('1');
                    islike = false;
                } else {
                    sb.append('0');
                    islike = false;
                }
            }

        }
        //最后一位进位了，还需要加一个 1
        if (islike){
            sb.append('1');
        }

        return String.valueOf(sb.reverse());

    }

    public static void main(String[] args) {
        LeetCode_67 code_67 = new LeetCode_67();
        String s = code_67.addBinary("0", "0");
        System.out.println(s);
    }
}
