package com.leetcode.newyear.february;

/**
 * @author fdn
 * @since 2022-02-13 21:45
 */
public class Test0213 {
    /**
     * 1189. “气球” 的最大数量
     * <p>
     * balloon
     *
     * @param text
     * @return
     */
    public int maxNumberOfBalloons(String text) {
        int a = 0, b = 0, l = 0, o = 0, n = 0;
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if (c == 'a') {
                a++;
            } else if (c == 'b') {
                b++;
            } else if (c == 'l') {
                l++;
            } else if (c == 'o') {
                o++;
            } else if (c == 'n') {
                n++;
            }
        }
        int count = Integer.MAX_VALUE;
        count = Math.min(count, a);
        count = Math.min(count, b);
        count = Math.min(count, l / 2);
        count = Math.min(count, o / 2);
        count = Math.min(count, n);
        return count;
    }
}
