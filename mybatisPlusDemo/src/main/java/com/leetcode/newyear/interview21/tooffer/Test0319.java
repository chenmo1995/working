package com.leetcode.newyear.interview21.tooffer;

/**
 * @author fdn
 * @since 2022-03-19 14:41
 */
public class Test0319 {
    /**
     * 剑指 Offer 04. 二维数组中的查找
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        //
        int a = 0, b = n - 1;
        while (a <= m - 1 && b >= 0) {
            if (matrix[a][b] > target) {
                b--;
            } else if (matrix[a][b] < target) {
                a++;
            } else {
                return true;
            }
        }
        return false;
    }
}
