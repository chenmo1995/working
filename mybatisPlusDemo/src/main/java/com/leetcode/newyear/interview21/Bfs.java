package com.leetcode.newyear.interview21;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author fdn
 * @since 2022-02-07 15:09
 */
public class Bfs {
    /**
     * 1765. 地图中的最高点
     *
     * @param isWater
     * @return
     */
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;
        // 构造一个队列，首先入队 “ 水域 ”，然后往周边扩散
        Deque<int[]> deque = new ArrayDeque<>();

        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    // " 水域 " 入队
                    deque.offerLast(new int[]{i, j});
                }
                // -1 代表还没有遍历
                result[i][j] = isWater[i][j] == 1 ? 0 : -1;
            }
        }
        // 注意下这里 <上下左右> 的写法
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // 开始遍历队列里面元素引申出来的其他元素
        while (!deque.isEmpty()) {
            int[] first = deque.pollFirst();
            int x = first[0], y = first[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    // 超出了边界
                    continue;
                }
                if (result[nx][ny] != -1) {
                    // 已经处理过了，不再遍历
                    continue;
                }
                result[nx][ny] = result[x][y] + 1;
                // 将新遍历的元素入队
                deque.offerLast(new int[]{nx, ny});
            }
        }
        return result;
    }
}
