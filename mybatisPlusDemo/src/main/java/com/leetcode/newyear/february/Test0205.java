package com.leetcode.newyear.february;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author fdn
 * @since 2022-02-10 12:33
 */
public class Test0205 {

    /**
     * 1219. 黄金矿工
     * 这一看又是平面的 BFS，多源 BFS？
     * 做着做着发现是回溯
     *
     * @param grid
     * @return
     */
    int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    int[][] grid;
    int max = 0;
    int m = 0;
    int n = 0;
    int sum = 0;

    public int getMaximumGold(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    dfs(i, j);
                }
            }
        }
        return max;
    }

    private void dfs(int x, int y) {
        int temp = grid[x][y];
        sum += temp;
        max = Math.max(max, sum);
        grid[x][y] = 0;
        for (int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == 0) {
                // 超出边界了 || 不能过去
                continue;
            }
            dfs(nx, ny);
        }
        sum -= temp;
        grid[x][y] = temp;
    }


}
