package com.leetcode.newyear.february;

/**
 * @author fdn
 * @since 2022-02-21 12:22
 */
public class Test0220 {

    /**
     * 717. 1 比特与 2 比特字符
     *
     * @param bits
     * @return
     */
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int i = 0;
        while (i < n) {
            if (i == n - 1 && bits[i] == 0) {
                return true;
            }
            if (bits[i] == 1) {
                i += 2;
            } else {
                i++;
            }
        }
        return false;
    }
}
