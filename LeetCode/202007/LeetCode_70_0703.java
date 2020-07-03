package July;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 */
/*
1	1						                                        1
2	1,1/2						                                    2
3	1,1,1/1,2/2,1					                                3
4	1,1,1,1/1,1,2/1,2,1/2,1,1/2,2				                    5
5	1,1,1,1,1/1,1,1,2/1,1,2,1/1,2,1,1/2,1,1,1/2,2,1/2,1,2/1,2,2	8

 */

public class LeetCode_70 {

    //超时了 。。。。 - 。-！
//    public int climbStairs(int n) {
//        if(n == 1){
//            return 1;
//        }else if ( n == 2){
//            return 2;
//        }else {
//            return climbStairs(n-1)+climbStairs(n-2);
//        }
//
//    }

    public int climbStairs(int n) {
        if(n <= 3){
            return n;
        }else {
            int num1 = 2;
            int num2 = 3;
            int tmp =0;
            for (int i = 3; i < n; i++) {
                tmp = num1 + num2;
                num1 = num2;
                num2 = tmp;
            }
            return tmp;
        }

    }

    public static void main(String[] args) {
        LeetCode_70 code_70 = new LeetCode_70();
        int stairs = code_70.climbStairs(28);
        System.out.println(stairs);
    }
}
