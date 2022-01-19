package com.leetcode.december;

/**
 * @author fdn
 * @since 2021-12-24 16:58
 */
public class Test122403 {

    /**
     * 37. 解数独
     * 从 1-9 ，每个数字都去空格中尝试，不符合条件的，就回退，符合的就进入下一个空格继续试
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        // 9 行 9 列

        backTracing(board);


    }

    /**
     * num 从 1-9
     *
     * @param board
     */
    private boolean backTracing(char[][] board) {
        // 终止条件 row == 8 && col == 8
//        if (row == 8 && col == 8) {
//            return;
//        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] != '.') {
                    // 这一位有数字，就继续下一个空格
                    continue;
                }
                for (char i = '1'; i <= '9'; i++) {
                    if (check(board, row, col, i)) {
                        // 如果满足现有的数独棋盘，就将 i 加入第 row 行，第 col 列
                        board[row][col] = i;
                        // 进行下一层递归
                        if (backTracing(board)) {
                            return true;
                        }
                        // 回溯
                        board[row][col] = '.';
                    }
                }
                // 9 个数都尝试完了，都不满足，则没有答案
                return false;
            }
        }
        return true;
    }


    private boolean check(char[][] board, int row, int col, char num) {
        // 这一行都不能出现过 num
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        // 这一列也不能出现过 num
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        // 所在九宫格里面不能出现过
        // startRow,startCol 0,3,6
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        // 在九宫格里面循环
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
