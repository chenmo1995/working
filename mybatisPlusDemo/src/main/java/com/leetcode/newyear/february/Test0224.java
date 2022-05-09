package com.leetcode.newyear.february;

/**
 * @author fdn
 * @since 2022-02-24 09:54
 */
public class Test0224 {

    /**
     * 1706. 球会落何处
     * <p>
     * - 如果当前位置 [i, j] 是 1，球会滚向右侧
     * -- 如果右侧是 1，球就会成功落到 [i+1, j+1]。特别的，当 j = n-1，会卡在墙上
     * -- 如果右侧是 -1，球就卡住了
     * - 当前位置 [i, j] 是 -1，球就会滚向左侧。
     * -- 如果右侧是 -1，球就会成功落到 [i+1, j-1]。特别的，当 j = 0，会卡在墙上
     * -- 如果右侧是 1，球就卡住了
     *
     * @param grid
     * @return
     */
    int[][] grid;
    int m;
    int n;

    public int[] findBall(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        this.grid = grid;
        this.m = m;
        this.n = n;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int index = whereIsBall(0, i);
            result[i] = index;
        }
        return result;
    }

    private int whereIsBall(int x, int y) {
        if ((y == 0 && grid[x][y] == -1) || (y == n - 1 && grid[x][y] == 1)) {
            return -1;
        }
        if (x == m - 1) {
            if (grid[x][y] == 1 && grid[x][y + 1] == 1) {
                return y + 1;
            }
            if (grid[x][y] == -1 && grid[x][y - 1] == -1) {
                return y - 1;
            }
        }
        if (grid[x][y] == 1 && grid[x][y + 1] == -1) {
            return -1;
        } else if (grid[x][y] == -1 && grid[x][y - 1] == 1) {
            return -1;
        } else if (grid[x][y] == 1 && grid[x][y + 1] == 1) {
            return whereIsBall(x + 1, y + 1);
        } else {
            return whereIsBall(x + 1, y - 1);
        }
    }
}
