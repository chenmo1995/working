package com.leetcode.newyear.january;

/**
 * @author fdn
 * @since 2022-01-17 11:37
 */
public class Test0117 {
    /**
     * 1220. 统计元音字母序列的数目
     *
     * @param n
     * @return
     */
    public int countVowelPermutation(int n) {
        long mod = (long) 1e9 + 7;
        // dp[i][j] 表示第 i 序号已经确定的字符串，且第 i 为 'aeiou' 中的一个，能组成字符串的种数
        long[][] dp = new long[n][5];
        dp[n - 1][0] = 1;
        dp[n - 1][1] = 1;
        dp[n - 1][2] = 1;
        dp[n - 1][3] = 1;
        dp[n - 1][4] = 1;

        for (int i = n - 2; i >= 0; i--) {
            dp[i][0] = dp[i + 1][1] % mod;
            dp[i][1] = (dp[i + 1][0] + dp[i + 1][2]) % mod;
            dp[i][2] = (dp[i + 1][0] + dp[i + 1][1] + dp[i + 1][3] + dp[i + 1][4]) % mod;
            dp[i][3] = (dp[i + 1][2] + dp[i + 1][4]) % mod;
            dp[i][4] = dp[i + 1][0] % mod;
        }
        long ans = 0;
        for (int i = 0; i < 5; i++) {
            ans += dp[0][i];
        }
        return (int)(ans % mod);
    }

    public static void main(String[] args) {
        long mod = (long) 10e9 + 7;
        System.out.println(mod);
    }
}
