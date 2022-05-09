package com.leetcode.newyear.interview21;

/**
 * @author fdn
 * @since 2022-02-28 15:31
 */
public class Number9 {

    /**
     * 29. 两数相除
     * a / b 结果一定是在 [0, a] 之中
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        long x = dividend, y = divisor;
        boolean flag = false;
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            flag = true;
        }
        if (x < 0) {
            x = -x;
        }
        if (y < 0) {
            y = -y;
        }
        long l = 0, r = x;
        while (l < r) {
            long mid = (r - l + 1 >> 1) + l;
            if (pow(y, mid) <= x) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        // 求得的 l 是满足 pow(y, mid) <= x 的最大的数
        long ans = flag ? l : -l;
        if (ans > Integer.MAX_VALUE || ans < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) ans;
    }

    /**
     * y * k
     *
     * @param y 被乘数
     * @param k 乘数
     * @return
     */
    private long pow(long y, long k) {
        long ans = 0;
        while (y != 0) {
            if ((y & 1) == 1) {
                ans += k;
            }
            y >>= 1;
            k += k;
        }
        return ans;
    }
}
