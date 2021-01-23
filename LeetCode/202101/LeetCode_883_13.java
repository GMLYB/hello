package January;

import org.junit.Test;

/**
 * 883. 三维形体投影面积
 * 在 N * N 的网格中，我们放置了一些与 x，y，z 三轴对齐的 1 * 1 * 1 立方体。
 *
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
 *
 * 现在，我们查看这些立方体在 xy、yz 和 zx 平面上的投影。
 *
 * 投影就像影子，将三维形体映射到一个二维平面上。
 *
 * 在这里，从顶部、前面和侧面看立方体时，我们会看到“影子”。
 *
 * 返回所有三个投影的总面积。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[[2]]
 * 输出：5
 * 示例 2：
 *
 * 输入：[[1,2],[3,4]]
 * 输出：17
 * 解释：
 * 这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
 *
 * 示例 3：
 *
 * 输入：[[1,0],[0,2]]
 * 输出：8
 * 示例 4：
 *
 * 输入：[[1,1,1],[1,0,1],[1,1,1]]
 * 输出：14
 * 示例 5：
 *
 * 输入：[[2,2,2],[2,1,2],[2,2,2]]
 * 输出：21
 *
 *
 * 提示：
 *
 * 1 <= grid.length = grid[0].length <= 50
 * 0 <= grid[i][j] <= 50
 */
public class LeetCode_883 {
    /*
        列向最大值
        横向最大值
        个数
     */
    public int projectionArea(int[][] grid) {
        int sum = 0;
        // 0 的个数
        int c0 = 0;
        // 列向
        for (int i = 0; i < grid.length; i++) {
            int row = 0;
            int col = 0;
            for (int j = 0; j < grid.length; j++) {
                col = Math.max(grid[i][j], col);
                row = Math.max(grid[j][i], row);
                if (grid[i][j] == 0) {
                    c0++;
                }
            }
            sum += col + row;
        }

        return sum + grid.length * grid.length - c0;

    }

    @Test
    public void test(){
        int[][] nums1 = new int[][]{{2,3},{2,4}};
        System.out.println(projectionArea(nums1));
    }
}

