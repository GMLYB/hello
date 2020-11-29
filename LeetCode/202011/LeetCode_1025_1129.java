package November;
/*

爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
选出任一 x，满足 0 < x < N 且 N % x == 0 。
用 N - x 替换黑板上的数字 N 。
如果玩家无法执行这些操作，就会输掉游戏。
只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 False。假设两个玩家都以最佳状态参与游戏。

示例 1：
输入：2
输出：true
解释：爱丽丝选择 1，鲍勃无法进行操作。

示例 2：
输入：3
输出：false
解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 
提示：
1 <= N <= 1000

 */
/*

N=1 f 必输
N=2 1 t 必胜
N=3 2 f 必输
N=4 3 t 喊3必胜
5 4 f 必输
6 5/3 喊3必胜 喊1或者2必输
7 6 f 必输
8 7 t 喊1必胜
9 8/6 f 必输
10 喊1必胜
11 必输
12 喊1必胜
13 必输
14 喊1必胜
15 必输
16 喊1必胜
17 必输
18 喊1必胜
19 必输
20 喊1必胜
... 感觉像奇数和偶数...
 */
public class LeetCode_1025 {
    public boolean divisorGame(int N) {
        if(N % 2 == 0){
            return true;
        }
        return false;
    }
}
