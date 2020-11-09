package November;
/*

    给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。

示例 1:
输入: 5
输出: 2
解释: 5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。

示例 2:
输入: 1
输出: 0
解释: 1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
 
注意:
    给定的整数保证在 32 位带符号整数的范围内。
    你可以假定二进制数不包含前导零位。


 */
public class LeetCode_476 {
    public int findComplement(int num) {
        if (num == 0) {
            return 1;
        }
        int sum = 0;
        int i = 0;
        while (num != 0) {
            if (num % 2 == 0) {
                sum += Math.pow(2, i);
            }
            num = num / 2;
            i++;
        }
        return sum;
    }
}
