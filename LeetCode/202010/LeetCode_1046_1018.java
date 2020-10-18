package October;

import org.junit.Test;
/*

有一堆石头，每块石头的重量都是正整数。
每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
如果 x == y，那么两块石头都会被完全粉碎；
如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。

示例：
输入：[2,7,4,1,8,1]
输出：1
解释：
先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
 
提示：
1 <= stones.length <= 30
1 <= stones[i] <= 1000


 */
public class LeetCode_1046 {


    public int lastStoneWeight(int[] stones) {
        int[] nums = new int[32];
        int max = 0;
        for (int stone : stones) {
            nums[stone] = (nums[stone] + 1) % 3;
//            nums[stone]++;
            if (stone > max){
                max = stone;
            }
        }
        int num1 = 0;
        int num2 = 0;
        boolean pd = false;
        for (int i = max; i >= 1; i--) {
            if (nums[i] > 0 || pd){
                //第一个赋值
                if (num1 == 0){
                    num1 = i;
                    nums[num1]--;
                    i = num1 + 1;
                }else {
                    //第二个赋值
                    if (num2 == 0){
                        num2 = i;
                        i++;
                        nums[num2]--;
                        pd = true;
                    }else {//都有值
                        i = num1 + 1;
                        pd = false;
                        //两个都有值的情况
                        if (num1 != num2) {
                            nums[num1 - num2]++;
                        }
                        num1 = 0;
                        num2 = 0;
                    }
                }
            }
        }
        System.out.println(num1);
        System.out.println(num2);
        return num1 - num2;
    }

    public int lastStoneWeight1(int[] stones) {
        int[] nums = new int[31];
        for (int stone : stones) {
            nums[stone] = (nums[stone] + 1) % 2;
        }
        int num1 = -1;
        int num2 = -1;
        for (int i = nums.length - 1; i >= 1; i--) {
            if (num1 == -1) {//最大的还没有赋值的情况
                //找出第一大的值
                if (nums[i] > 0) {
                    num1 = i;
                }
            } else {//最大值找出来的情况
                //第二大值还没有找出来的情况
                if (num2 == -1) {
                    if (nums[i] > 0) {
                        num2 = i;
                    }
                } else {
                    i = num2;
                    //两个都有值的情况
                    if (num1 == num2) {
                        nums[num1] -= 2;
                    } else {
                        nums[num1]--;
                        nums[num2]--;
                        nums[num1 - num2]++;
                        num1 = -1;
                        num2 = -1;
                    }
                }
            }
        }
        return num1 == -1 ? 0 : num1;
    }

    @Test
    public void test(){
        int[] stones1 = new int[]{6,6,4,9,4,6,3};
        int[] stones2 = new int[]{1,1,2,4,7,8};
        int[] stones3 = new int[]{8,4,10};
        System.out.println(lastStoneWeight(stones1));
    }
}
