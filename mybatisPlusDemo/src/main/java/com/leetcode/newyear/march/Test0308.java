package com.leetcode.newyear.march;

/**
 * @author fdn
 * @since 2022-03-08 12:37
 */
public class Test0308 {

    /**
     * 2055. 蜡烛之间的盘子
     * 这应该是要 dp 吧，好像 dp 的复杂度太高了
     * 结合双指针，而不是遍历呢？
     * <p>
     * 其实是前缀和 + 二分，继续优化成前缀和 + 预处理
     *
     * @param s
     * @param queries
     * @return
     */
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];

        int m = s.length();
        // 记录 i 位置之前（包括 i）一共有多少个盘子 count[i]
        int[] count = new int[m];
        // 记录距离 i 位置最近的蜡烛的位置，分别是左和右
        int[][] index = new int[m][2];
        count[0] = s.charAt(0) == '*' ? 1 : 0;
        index[0][0] = s.charAt(0) == '*' ? -1 : 0;
        for (int i = 1; i < m; i++) {
            if (s.charAt(i) == '*') {
                count[i] = count[i - 1] + 1;
                index[i][0] = index[i - 1][0];
            } else {
                count[i] = count[i - 1];
                index[i][0] = i;
            }
        }
        index[m - 1][1] = s.charAt(m - 1) == '*' ? -1 : m - 1;
        for (int i = m - 2; i >= 0; i--) {
            if (s.charAt(i) == '*') {
                index[i][1] = index[i + 1][1];
            } else {
                index[i][1] = i;
            }
        }
        for (int i = 0; i < n; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int indexL = index[l][1];
            int indexR = index[r][0];
            if (indexL >= r || indexR <= l || indexL == -1 || indexR == -1) {
                ans[i] = 0;
            } else {
                ans[i] = count[indexR] - count[indexL];
            }
        }
        return ans;
    }
}
