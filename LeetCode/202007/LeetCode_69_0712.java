package July;

/*
实现 int sqrt(int x) 函数。
计算并返回 x 的平方根，其中 x 是非负整数。
由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

示例 1:
输入: 4
输出: 2

示例 2:
输入: 8
输出: 2
说明: 8 的平方根是 2.82842...,
     由于返回类型是整数，小数部分将被舍去。
 */

public class LeetCode_69 {
    public int mySqrt(int x) {
        if (x < 2){
            return x;
        }
        int p1 = 0;
        int p2 = x;
        while (p1 <= p2) {
            int tmp = (p1 + p2) / 2;
            if (tmp > x/tmp) { //一开始写if里面写 tmp * tmp > x ,else if里面写 tmp * tmp < x ,结果数字太大的时候，超时了，所以改成了除法
                p2 = tmp-1;
//                System.out.println("p2 = "+p2);
            } else if (tmp < x/tmp) {
                p1 = tmp+1;
//                System.out.println("p1 = "+p1);
            }else {
                return tmp;
            }
        }
        return p1-1;
    }

    public static void main(String[] args) {
        LeetCode_69 code = new LeetCode_69();
        System.out.println(code.mySqrt(2));
    }
}
