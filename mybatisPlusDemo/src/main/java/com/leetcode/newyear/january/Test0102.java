package com.leetcode.newyear.january;

/**
 * @author fdn
 * @since 2022-01-02 10:55
 */
public class Test0102 {

    /**
     * 390. 消除游戏
     * 看到 n 的取值可以很大，我就没用暴力了，因为估计会超时
     * <p>
     * 我尝试了很多个数，分析到了最终列表里面剩余的是一个等差数列，且次数越多，数列的公差越大，（每次乘以 2）
     * 须不知我只要记录每次消除之后的个数（因为最终要剩一个），还有 a1 和 an 就好了，公差也要记录
     *
     * @param n
     * @return
     */
    public int lastRemaining(int n) {
        // 轮次，决定从前往后还是从后往前删
        int k = 1;
        int a1 = 1, an = n, q = 1, count = n;
        while (count > 1) {
            if (k % 2 == 1) {
                // 从前往后消除
                a1 = a1 + q;
                an = count % 2 == 1 ? an - q : an;
            } else {
                // 从后往前消除
                a1 = count % 2 == 1 ? a1 + q : a1;
                an = an - q;
            }
            q <<= 1;
            count >>= 1;
            k++;
        }
        return a1;
    }
}
