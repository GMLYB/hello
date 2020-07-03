package day6_16;

import java.math.BigInteger;

/**
 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

 示例 1:
 输入: 123
 输出: 321

 示例 2:
 输入: -123
 输出: -321

 示例 3:
 输入: 120
 输出: 21

 注意:
 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。

 */



public class LeetCode_7 {

    public int reverse(int x) {
        int orgin = x;
        if(x < 0){
            x *= -1;
        }
        String s1 = String.valueOf(x);
        int[] max = {2,1,4,7,4,8,3,6,4,7};
        if(s1.length() > max.length){
            return 0;
        }else if(s1.length()<max.length){
            int sum = 0;
            for (int i = 0; i < s1.length(); i++) {
                sum += Math.pow(10,s1.length()-i-1) * Integer.parseInt(String.valueOf(s1.charAt(s1.length()-i-1)));
            }
            if(orgin < 0){
                sum *= -1;
            }
            return sum;
        }else {
            int[] num = new int[s1.length()];
            for (int i = 0; i < s1.length(); i++) {
                num[i] = Integer.parseInt(String.valueOf(s1.charAt(s1.length()-i-1)));
            }
            int sum = 0;
            for (int i = 0; i < s1.length(); i++) {
                sum += Math.pow(10,s1.length()-i-1) * num[i];
            }
            if (sum >= 2147483647){
                return 0;
            }
            if(orgin < 0){
                sum *= -1;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        LeetCode_7 code7 = new LeetCode_7();
        int x = code7.reverse(-2147483412);
        System.out.println(x);
    }

}
