package day6_17;

import static java.lang.Math.log10;

/**
 *
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 示例 1:
 * 输入: 121
 * 输出: true
 *
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 进阶:
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 */

public class LeetCode_9 {

//    public boolean isPalindrome(int x) {
//        int a = x;
//        if(x < 0 || x == 2147483647){
//            return false;
//        }
//        int t = 0;
//        int i = String.valueOf(x).length();
//        while (x != 0){
//            t += x % 10*Math.pow(10,--i);
//            x = x / 10;
//        }
//        if(t == a){
//            return true;
//        }else {
//            return false;
//        }
//
//    }

    public boolean isPalindrome(int x){
        if(x<0){
            return false;
        }
        int t = 0;
        int w = 0;
        int a = x;
        int i = String.valueOf(x).length();
        int k = 0;
//        int i=(int)log10(x)+1;
//        System.out.println(i);
        int j = i / 2;
        while (i != j){
            t = (int) (a / (1 * Math.pow(10,--i)));
            w = x % 10;
            if(t != w){
                return false;
            }
            a -= (int) (t * (1 * Math.pow(10,i)));
            x = x / 10;
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode_9 code9 = new LeetCode_9();
        System.out.println(code9.isPalindrome(2147483647));
    }
}
