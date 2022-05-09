package com.leetcode.newyear.interview21;

/**
 * @author fdn
 * @since 2022-02-16 14:40
 */
public class MemorySearch {

    /**
     * 剑指 Offer 10- I. 斐波那契数列
     *
     * @param n
     * @return
     */
    int[] fib = new int[101];
    int MOD = (int) (1e9 + 7);

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        if (fib[n] != 0) {
            return fib[n];
        }
        int result = (fib(n - 1) + fib(n - 2)) % MOD;
        fib[n] = result;
        return result;
    }

    /**
     * 375. 猜数字大小 II
     * 把从 i 到 j，猜对数字需要的最少钱记录下来
     *
     * @param n
     * @return
     */
    // static 修饰可以确保每个区间的计算在所有样例中只会发生一次。
    static int[][] money = new int[201][201];

    public int getMoneyAmount(int n) {
        return dfs(1, n);
    }

    private int dfs(int x, int y) {
        if (x >= y) {
            return 0;
        }
        if (money[x][y] != 0) {
            return money[x][y];
        }
        int result = Integer.MAX_VALUE;
        for (int i = x; i <= y; i++) {
            // 以 i 分割，在左右两边选最大的数，因为每种可能都要满足
            int cur = Math.max(dfs(x, i - 1), dfs(i + 1, y)) + i;
            // 满足最坏情况的最小值
            result = Math.min(cur, result);
        }
        money[x][y] = result;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(1e9 + 7);
    }
}
