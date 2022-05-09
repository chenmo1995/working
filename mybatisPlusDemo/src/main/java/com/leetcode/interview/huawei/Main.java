package com.leetcode.interview.huawei;

import java.util.Scanner;

/**
 * @author fdn
 * @since 2022-04-07 22:11
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String t = in.nextLine();
        String p = in.nextLine();
        int[] next = getNext(p);
        int ans = kpm(t, p);
        System.out.println(ans);
    }

    /**
     * aabaabf
     * aabf
     *
     * @param p
     * @return
     */
    private static int[] getNext(String p) {
        int n = p.length();
        char[] chars = p.toCharArray();
        int[] next = new int[n];
        next[0] = -1;
        // j 表示前缀字符串的最后一个位置，i 表示后缀字符串的第一个位置，j 代表了相等前后缀的长度，即 next 数组的值
        int i = 1, j = 0;
        for (; i < n; i++) {
            while (i < n && chars[j] != chars[i]) {
                next[i] = -1;
                i++;
            }
            if (i < n) {
                next[i] = j;
                j++;
            } else {
                j = next[j];
            }
        }


        return next;
    }

    /**
     * 从文本串 t 中找到匹配串 p
     *
     * @param t
     * @param p
     * @return
     */
    private static int kpm(String t, String p) {

        return 0;
    }
}
