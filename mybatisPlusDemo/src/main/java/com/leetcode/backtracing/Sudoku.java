package com.leetcode.backtracing;

/**
 * 37. 解数独
 * 从第一行开始，从左到右
 * 怎么就结束了呢
 *
 * @author fdn
 * @since 2022-04-25 16:48
 */
public class Sudoku {

    char[][] board;

    public void solveSudoku(char[][] board) {
        this.board = board;
        backTracing();
    }

    private boolean backTracing() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        if (check(i, j, k)) {
                            board[i][j] = k;
                            if (backTracing()) {
                                return true;
                            }
                            board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean check(int row, int col, char num) {
        // 校验行
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        // 校验列
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        // 校验 3*3
        int x = row / 3 * 3;
        int y = col / 3 * 3;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int a = '1' - (char) 1; // 48
        System.out.println(a);
    }
}
