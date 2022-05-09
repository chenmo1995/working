package com.leetcode.newyear.february;

import java.util.Arrays;

/**
 * @author fdn
 * @since 2022-02-17 09:42
 */
public class Test0217 {

    /**
     * 688. 骑士在棋盘上的概率
     * 这里的记忆化搜索其实没有起到记忆的作用，因为 k 每次遍历都减小的缘故，利用不到之前的数据
     * 但是却给我们提供了另一条思路 ---> 动态规划，就以记忆化搜索的参数作为 dp 的参数
     *
     * @param n
     * @param k
     * @param row
     * @param column
     * @return
     */
    int[][] dirs = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
    static double[][][] used;

    public double knightProbability(int n, int k, int row, int column) {
        used = new double[n][n][k + 1];
        Arrays.fill(used, 1);
        return dfs(n, k, row, column);
    }

    private double dfs(int n, int k, int row, int column) {
        if (k == 0) {
            return 1;
        }
        if (used[row][column][k] != 1) {
            return used[row][column][k];
        }
        double result = 0;
        for (int[] dir : dirs) {
            int nRow = dir[0] + row, nColumn = dir[1] + column;
            if (nRow < 0 || nRow >= n || nColumn < 0 || nColumn >= n) {
                continue;
            }
            double rate = dfs(n, k - 1, nRow, nColumn) / 8;
            result += rate;
        }
        used[row][column][k] = result;
        return result;
    }

    /**
     * 688. 骑士在棋盘上的概率
     * dp；dp[i][j][k] 表示骑士在 (i, j) 位置，走了 k 步，可以留在棋盘的概率
     * dp[i][j][k] = ? dp[x][y][k-1] 能到达 (i, j) 位置的其他 8 个位置分别的 1/8 之和
     *
     * @param n
     * @param k
     * @param row
     * @param column
     * @return
     */
    public double knightProbability2(int n, int k, int row, int column) {
        double[][][] dp = new double[n][n][k + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int m = 1; m < k + 1; m++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int[] dir : dirs) {
                        int nx = dir[0] + i, ny = dir[1] + j;
                        if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                            continue;
                        }
                        dp[i][j][m] += dp[nx][ny][m - 1] / 8;
                    }
                }
            }
        }
        return dp[row][column][k];
    }
}
