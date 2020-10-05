package October;

import java.util.ArrayList;
import java.util.List;
/*

给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
找到所有在 [1, n] 范围之间没有出现在数组中的数字。
您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

示例:
输入:
[4,3,2,7,8,2,3,1]
输出:
[5,6]

 */
public class LeetCode_448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] num = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            num[nums[i]-1]++;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            if (num[i] == 0) {
                list.add(i+1);
            }
        }
        return list;
    }
}
