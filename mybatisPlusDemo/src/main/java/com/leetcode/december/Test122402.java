package com.leetcode.december;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fdn
 * @since 2021-12-24 15:10
 */
public class Test122402 {

    /**
     * 51. N 皇后
     *
     * @param n
     * @return
     */
    List<List<String>> result;
    List<String> path;

    public List<List<String>> solveNQueens(int n) {

        result = new ArrayList<>();
        path = new ArrayList<>();
        backTracing(n, 0);
        return result;
    }

    /**
     * 收集、终止、单层递归
     * 需要用 startIndex 来控制行数 (不行！不能用 startIndex 控制行数，)。棋盘的宽度就是 for 循环的宽度，棋盘的深度就是递归的深度
     * <p>
     * 每一层递归就是新的一行
     */
    private void backTracing(int n, int startIndex) {

        char[] row = new char[n];
        Arrays.fill(row, '.');

        // 收集 ;startIndex 最多可以达到 n-1 行，n-1 行依然需要处理，再下一层递归，就可以收集了
        if (startIndex == n) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 终止。我们一行一行的放置皇后，同一行肯定是不会出现皇后的
        // 终止条件就是本层循环里面，皇后出现了相互攻击的情况（新加入的皇后 “横竖斜” 有其它皇后）

        // 单次递归; startIndex 来控制行数 ， i 来控制单行内的哪一列
        for (int i = 0; i < n; i++) {
            // 符合王后不攻击的情况就添加进 path
            // 肯定需要当前棋盘的状态，然后加上哪一行和哪一列
            if (check(path, startIndex, i, n)) {
                // 往 path 里面加入 "Q"
                row[i] = 'Q';
                path.add(startIndex, new String(row));
                backTracing(n, startIndex + 1);
                row[i] = '.';
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 检查当前位置可不可以放
     *
     * @param path
     * @param row
     * @param col
     * @param n
     * @return
     */
    private boolean check(List<String> path, int row, int col, int n) {
        // 一行只加了一个 "Q" 所以不需要校验同一行
        // 检查每一行的对应列
        for (int i = 0; i < row; i++) {
            if (path.get(i).charAt(col) == 'Q') {
                return false;
            }
        }
        // 检查 45° 角;从当前行往上遍历,从当前列往左遍历
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (path.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        // 检查 135° 角;从当前行往上遍历,从当前列往右遍历
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (path.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }
}

















