package com.leetcode.newyear.march;

import java.util.Arrays;

/**
 * @author fdn
 * @since 2022-03-01 10:24
 */
public class Test0301 {

    /**
     * 6. Z 字形变换
     * 每一组有 2*N - 2 个字母
     * 假设 N = 4，每组 6 个
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j - 2 * i < n; j += numRows * 2 - 2) {
                if (i != 0 && i != numRows - 1 && j >= 2 * i) {
                    sb.append(s.charAt(j - 2 * i));
                }
                if (j < n) {
                    sb.append(s.charAt(j));
                }
            }
        }
        return sb.toString();
    }
}
