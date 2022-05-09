package com.leetcode.newyear.february;

import java.util.*;

/**
 * @author fdn
 * @since 2022-02-13 10:17
 */
public class Test0212 {

    /**
     * 1020. 飞地的数量
     *
     * @param grid
     * @return
     */
    int[][] grid;
    int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    int m;
    int n;

    public int numEnclaves(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            dfs(i, 0);
            dfs(i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            dfs(0, i);
            dfs(m - 1, i);
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int x, int y) {
        if (grid[x][y] == 0) {
            return;
        }
        grid[x][y] = 0;
        for (int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == 0) {
                continue;
            }
            dfs(nx, ny);
        }
    }

    /**
     * 多源 bfs
     * 将边界上的陆地入队，然后对每个元素进行 bfs 遍历
     *
     * 【没有记录已经访问过的位置，反复横跳，肯定超时】
     *
     * @param grid
     * @return
     */
    public int numEnclaves2(int[][] grid) {
        Deque<int[]> deque = new ArrayDeque<>();
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) {
                deque.offerLast(new int[]{i, 0});
            }
            if (grid[i][n - 1] == 1) {
                deque.offerLast(new int[]{i, n - 1});
            }
        }
        for (int i = 0; i < n; i++) {
            if (grid[0][i] == 1) {
                deque.offerLast(new int[]{0, i});
            }
            if (grid[m - 1][i] == 1) {
                deque.offerLast(new int[]{m - 1, i});
            }
        }
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            grid[cur[0]][cur[1]] = 0;
            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0], ny = cur[1] + dir[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx][ny] == 0) {
                    continue;
                }
                deque.offerLast(new int[]{nx, ny});
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
