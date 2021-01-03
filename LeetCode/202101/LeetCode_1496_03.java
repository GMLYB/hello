package January;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
/*

1496. 判断路径是否相交
给你一个字符串 path，其中 path[i] 的值可以是 'N'、'S'、'E' 或者 'W'，分别表示向北、向南、向东、向西移动一个单位。

机器人从二维平面上的原点 (0, 0) 处开始出发，按 path 所指示的路径行走。

如果路径在任何位置上出现相交的情况，也就是走到之前已经走过的位置，请返回 True ；否则，返回 False 。



示例 1：



输入：path = "NES"
输出：false
解释：该路径没有在任何位置相交。
示例 2：



输入：path = "NESWW"
输出：true
解释：该路径经过原点两次。


提示：

1 <= path.length <= 10^4
path 仅由 {'N', 'S', 'E', 'W} 中的字符组成

 */
public class LeetCode_1496 {

    public boolean isPathCrossing(String path) {
        int En = 0;
        int Wn = 0;
        int Nn = 0;
        int Sn = 0;
        for (char c : path.toCharArray()) {
            if (c == 'E') {
                En++;
            } else if (c == 'W') {
                Wn++;
            } else if (c == 'N') {
                Nn++;
            } else {
                Sn++;
            }
        }
        int[][] indexs = new int[Math.max(En, Wn) * 2 + 1][Math.max(Nn, Sn) * 2 + 1];
        int x = Math.max(En, Wn);
        int y = Math.max(Nn, Sn);
        indexs[x][y] = 1;
        for (char c : path.toCharArray()) {
            if (c == 'N') {
                y++;
            } else if (c == 'S') {
                y--;
            } else if (c == 'E') {
                x++;
            } else {
                x--;
            }
            if (indexs[x][y] == 1) {
                return true;
            }
            indexs[x][y] = 1;
        }
        return false;
    }

    public boolean isPathCrossing1(String path) {
        int x = 0;
        int y = 0;
        Set<String> set = new HashSet<>();
        for (char c : path.toCharArray()) {
            if (c == 'N') {
                x++;
            } else if (c == 'S') {
                x--;
            } else if (c == 'E') {
                y++;
            } else {
                y--;
            }
            if (set.contains(x + "-" + y)) {
                return true;
            }
            set.add(x + "-" + y);
        }
        return false;
    }

    @Test
    public void test() {
        System.out.println(isPathCrossing("S"));
    }
}
