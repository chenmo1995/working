package com.leetcode.newyear.february;

import java.util.ArrayList;
import java.util.List;

/**
 * 1380. 矩阵中的幸运数
 *
 * @author fdn
 * @since 2022-02-15 09:35
 */
public class Test0215 {
    /**
     * @param matrix
     * @return
     */
    public List<Integer> luckyNumbers(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // rowMin[i] 表示第 i 行的最小值的列数
        int[] rowMin = new int[m];
        // colMax[i] 表示第 i 列的最大值的行数
        int[] colMax = new int[n];
        for (int i = 0; i < m; i++) {
            rowMin[i] = getMin(matrix, i, n);
        }
        for (int i = 0; i < n; i++) {
            colMax[i] = getMax(matrix, i, m);
        }
        List<Integer> list = new ArrayList<>();
        // 当 y = rowMin[x]， x == colMax[y] 时，是幸运数
        for (int x = 0; x < m; x++) {
            int y = rowMin[x];
            if (colMax[y] == x) {
                list.add(matrix[x][y]);
            }
        }
        return list;
    }

    private int getMax(int[][] matrix, int index, int m) {
        int max = Integer.MIN_VALUE;
        int location = -1;
        for (int i = 0; i < m; i++) {
            if (matrix[i][index] > max) {
                max = matrix[i][index];
                location = i;
            }
        }
        return location;
    }

    private int getMin(int[][] matrix, int index, int n) {
        int min = Integer.MAX_VALUE;
        int location = -1;
        for (int i = 0; i < n; i++) {
            if (matrix[index][i] < min) {
                min = matrix[index][i];
                location = i;
            }
        }
        return location;
    }
}
