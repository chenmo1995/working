package com.leetcode.newyear.january;

/**
 * @author fdn
 * @since 2022-01-01 12:55
 */
public class Test0101 {
    public int[][] construct2DArray(int[] original, int m, int n) {
        int[][] result = new int[m][n];
        if (original.length != m * n) {
            return new int[][]{};
        }
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = original[index++];
            }
        }
        return result;
    }
}
