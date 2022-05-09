package com.leetcode.interview.huawei;

import java.util.Scanner;

/**
 * @author fdn
 * @since 2022-04-07 20:41
 */
public class MainA {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int ans = longestSubStr(s);
        System.out.println(ans);
    }

    /**
     * 只包含字母和数字
     * <p>
     * 1、子串只包含一个字母，其余全是数字
     * 2、字母可以在任意位置
     * <p>
     * 双指针
     * <p>
     * 先找到第一个字母，找到第二个字母的时候，就可以计算出包含第一个字母的子串的长度
     * 找到第三个字母的时候，就可以计算出包含第二个字母的子串的长度
     *
     * @param s
     * @return
     */
    private static int longestSubStr(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        // 去掉 s 首部的字母
        int k = 0;
        for (; k < n; k++) {
            char c = chars[k];
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                continue;
            } else {
                break;
            }
        }
        if (k == n) {
            return -1;
        }
        // k 是第一个数字出现的位置
        int index = k + 1;

        for (; index < n; index++) {
            char c = s.charAt(index);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                break;
            }
        }
        if (index == n) {
            if (k == 0) {
                return -1;
            } else {
                return n - k + 1;
            }
        }
        // index 是出现数字后，出现的第一个字母的位置
        int a = k, b = index;
        int ans = index - k + 1;
        for (int i = index + 1; i < n; i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                // 找到字母了
                ans = Math.max(ans, i - a);
                a = b + 1;
                b = i;
            } else {
                ans = Math.max(ans, i - a + 1);
            }
        }
        return ans;
    }
}
