package com.leetcode.backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N 皇后
 * 回溯啊，怎么回溯啊
 * 从第一行开始往下面，找位子；
 * 无法放置的时候，就回溯；怎么就算无法放置呢，当然是没有返回 true 了
 *
 * @author fdn
 * @since 2022-04-25 15:29
 */
public class NQueen {
    public static void main(String[] args) {
        NQueen test = new NQueen();
        test.solveNQueens(4);
    }

    List<List<String>> result = new ArrayList<>();
    List<String> path = new ArrayList<>();
    int n;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        // 参数代表第几行，一行一行的试
        backTracing(0);
        return result;
    }

    private void backTracing(int row) {
        // 收集
        if (row == n) {
            result.add(new ArrayList<>(path));
            return;
        }
        char[] chars = new char[n];
        Arrays.fill(chars, '.');

        // 单层循环
        for (int col = 0; col < n; col++) {
            // col 表示在第几列放置皇后，row 代表在第几行放置，每一层递归加一行
            // 校验当前棋盘，[row, col] 位置放置皇后是否可行
            if (check(path, row, col)) {
                chars[col] = 'Q';
                path.add(new String(chars));
                // 放置下一行
                backTracing(row + 1);
                chars[col] = '.';
                path.remove(path.size() - 1);
            }
            // 不满足就去尝试下一列
        }
    }

    private boolean check(List<String> path, int row, int col) {
        // 行不用校验，因为是一行一行尝试的
        // 校验列
        for (int i = row - 1; i >= 0; i--) {
            if (path.get(i).charAt(col) == 'Q') {
                return false;
            }
        }
        // 校验左斜，校验右上方就行了，上方才有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (path.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        // 校验右斜，校验左上方
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; j--, i--) {
            if (path.get(i).charAt(j) == 'Q') {
                return false;
            }
        }
        return true;
    }
}
