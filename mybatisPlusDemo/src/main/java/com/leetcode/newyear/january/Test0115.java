package com.leetcode.newyear.january;

/**
 * @author fdn
 * @since 2022-01-15 14:25
 */
public class Test0115 {

    /**
     * @param n
     * @return
     */
    public int totalMoney(int n) {
        int i = n / 7;
        int result = 0;
        result += i * 28;
        for (int j = 1; j < i; j++) {
            result += j * 7;
        }
        int remain = n % 7;
        while (remain > 0) {
            result += remain-- + i;
        }
        return result;
    }
}
