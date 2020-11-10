package November;

import org.junit.Test;

import java.util.Arrays;
/*

给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。
（我们可以多次选择同一个索引 i。）
以这种方式修改数组后，返回数组可能的最大和。

示例 1：
输入：A = [4,2,3], K = 1
输出：5
解释：选择索引 (1,) ，然后 A 变为 [4,-2,3]。

示例 2：
输入：A = [3,-1,0,2], K = 3
输出：6
解释：选择索引 (1, 2, 2) ，然后 A 变为 [3,1,0,2]。

示例 3：
输入：A = [2,-3,-1,5,-4], K = 2
输出：13
解释：选择索引 (1, 4) ，然后 A 变为 [2,3,-1,5,4]。
 
提示：
    1 <= A.length <= 10000
    1 <= K <= 10000
    -100 <= A[i] <= 100


 */
public class LeetCode_1005 {
    public int largestSumAfterKNegations(int[] A, int K) {

        for (int i = 0; i < K; i++) {
            Arrays.sort(A);
            if (A[0] == 0){
                break;
            }else {
                A[0] = -A[0];
            }
        }
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        return sum;
    }

    @Test
    public void test(){
        int[] nums = new int[]{0,6,9,-3,-1,3,-8};
        System.out.println(largestSumAfterKNegations(nums, 6));
    }
}
