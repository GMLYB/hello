package October;

import org.junit.Test;

/*

在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。
如果小镇的法官真的存在，那么：
小镇的法官不相信任何人。
每个人（除了小镇法官外）都信任小镇的法官。
只有一个人同时满足属性 1 和属性 2 。
给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。
如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1。

示例 1：
输入：N = 2, trust = [[1,2]]
输出：2

示例 2：
输入：N = 3, trust = [[1,3],[2,3]]
输出：3

示例 3：
输入：N = 3, trust = [[1,3],[2,3],[3,1]]
输出：-1

示例 4：
输入：N = 3, trust = [[1,2],[2,3]]
输出：-1

示例 5：
输入：N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
输出：3

提示：
1 <= N <= 1000
trust.length <= 10000
trust[i] 是完全不同的
trust[i][0] != trust[i][1]
1 <= trust[i][0], trust[i][1] <= N

 */

public class LeetCode_997 {
    public int findJudge(int N, int[][] trust) {
        int[] ren = new int[N];
        int[] piao = new int[N];
        //记录投票的人和得票统计
        for (int[] ints : trust) {
            ren[ints[0] - 1]++;
            piao[ints[1] - 1]++;
        }
        int index = 0;
        //得票数为总人数-1的，就有可能是法官
        for (int i : piao) {
            System.out.println("i = " + i);
            if (i == (N - 1)) {
                System.out.println("ren = " + ren[index]);
                //没投票的，一定就是法官
                if (ren[index] == 0) {
                    return index + 1;
                }
            }
            index++;
        }
        return -1;
    }

    @Test
    public void test() {
        int n1 = 4;
        int[][] nums1 = {{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}};
        System.out.println(findJudge(n1, nums1));
    }
}
