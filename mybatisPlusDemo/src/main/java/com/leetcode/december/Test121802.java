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
     * 39. 组合总和
     *
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backTracing(0, target, candidates);
        return result;
    }

    private void backTracing(int sum, int target, int[] candidates) {
        if (sum == target) {
            List<Integer> temp = new ArrayList<>(path);
            path.sort((a, b) -> a - b);
            if (!result.contains(path)) {
                result.add(new ArrayList<>(path));
            }
            path = temp;
            return;
        }
        for (int i = 0; i < candidates.length && sum + candidates[i] <= target; i++) {
            path.add(candidates[i]);
            sum += candidates[i];
            backTracing(sum, target, candidates);
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }

    /**
     * 这个方法没有排序，也没有去重，效率高不少
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        backTracing2(0, target, candidates, 0);
        return result;
    }

    private void backTracing2(int sum, int target, int[] candidates, int startIndex) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        // && sum + candidates[i] <= target 这个for循环的判断条件要谨慎
        // 一方面是为了发现 sum + candidates[i] > target 就终止本次for循环，不必等到下一层递归通过 sum > target 来判断
        // 但是另一方面，这个条件的使用需要数组 candidates 是顺序排列的状态，如果不是，提前终止的循环会导致后面符合条件
        // 的搜索结果也没有被搜索到
        for (int i = startIndex; i < candidates.length; i++) {
            path.add(candidates[i]);
            sum += candidates[i];
            // 不用i+1了，表示可以重复读取当前的数
            backTracing2(sum, target, candidates, i);
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }

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















