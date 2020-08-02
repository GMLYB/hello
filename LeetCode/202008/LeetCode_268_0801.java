package August;

/*

给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。

示例 1:
输入: [3,0,1]
输出: 2

示例 2:
输入: [9,6,4,2,3,5,7,0,1]
输出: 8

 */

public class LeetCode_268 {

    public int missingNumber(int[] nums) {

        int sum = 0;
        // （ 首项 + 尾项 ）* 项数 / 2
        int count = (0 + nums.length) * (nums.length+1) / 2;

        for (int n : nums){
            sum = sum + n;
        }
        return count - sum;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{3,0,1};
        int[] nums = new int[]{9,6,4,2,3,5,7,0,1};
        LeetCode_268 code = new LeetCode_268();
        System.out.println(code.missingNumber(nums));
    }
}
