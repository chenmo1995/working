package com.leetcode.newyear.february;

/**
 * @author fdn
 * @since 2022-02-08 14:00
 */
public class Test0201 {

    /**
     * 1763. 最长的美好子字符串
     *
     * @param s
     * @return
     */
    public String longestNiceSubstring(String s) {
        int n = s.length();
        int index = -1;
        int len = 0;
        for (int i = 0; i < n; i++) {
            int a = 0, b = 0;
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                if (c - 'a' >= 0 && c - 'a' <= 26) {
                    // 用 int 的低 26 位中对应一位记录出现了小写字符
                    a |= 1 << (c - 'a');
                } else {
                    // 记录大写字符是否出现
                    b |= 1 << (c - 'A');
                }
                // a == b 时，就是美好字符串
                if (a == b && j - i + 1 > len) {
                    index = i;
                    len = j - i + 1;
                }
            }
        }
        return index == -1 ? "" : s.substring(index, index + len);
    }
}
