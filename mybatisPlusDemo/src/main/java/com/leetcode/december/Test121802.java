package com.leetcode.december;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fdn
 * @since 2021-12-18 12:49
 */
public class Test121802 {



    /**
     * 419. 甲板上的战舰
     *
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        // m行
        int m = board.length;
        // n列
        int n = board[0].length;
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    board[i][j] = '.';
                    for (int k = j + 1; k < n && board[i][k] == 'X'; k++) {
                        board[i][k] = '.';
                    }
                    for (int k = i + 1; k < m && board[k][j] == 'X'; k++) {
                        board[k][j] = '.';
                    }
                    sum++;
                }
            }
        }
        return sum;
    }

    /**
     * 如果某个位置是X，且他的左方和上方不是X，可以认为他就是战舰的头部
     * 注意考虑边界条件
     *
     * @param board
     * @return
     */
    public int countBattleships2(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j - 1] == 'X') {
                        continue;
                    }
                    sum++;
                }
            }
        }
        return sum;
    }
}















