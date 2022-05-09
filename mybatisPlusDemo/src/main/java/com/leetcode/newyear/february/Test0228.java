package com.leetcode.newyear.february;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fdn
 * @since 2022-02-28 09:48
 */
public class Test0228 {
    /**
     * 1601. 最多可达成的换楼请求数目
     *
     * @param n
     * @param requests
     * @return
     */
    public int maximumRequests(int n, int[][] requests) {
        int m = requests.length;
        int ans = 0;
        // 用 1 << m 来记录所有的可能性,m 最大为 16，i < 2^16
        for (int i = 0; i < (1 << m); i++) {
            int cnt = getCnt(i);
            if (cnt <= ans) {
                continue;
            }
            if (check(requests, i, n)) {
                ans = cnt;
            }
        }
        return ans;
    }

    private boolean check(int[][] requests, int i, int n) {

        int[] change = new int[n];
        // sum 用来记录 change 数组中值不为 0 的数的个数，
        int sum = 0;
        for (int j = 0; j < 16; j++) {
            if (((i >> j) & 1) == 1) {
                if (change[requests[j][0]]-- == 1) {
                    sum++;
                }
                if (change[requests[j][1]]++ == 0) {
                    sum--;
                }
            }
        }
        return sum == 0;
    }

    private int getCnt(int i) {
        int cnt = 0;
        // j & (-j) 求的是 j 的二进制数最低位为１的权值
        for (int j = i; j > 0; j -= (j & (-j))) {
            cnt++;
        }
        return cnt;
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * 两个字符串的词频相同，即时字母异位词
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        int[] cnt = new int[26];
        int n = s.length();
        int m = p.length();
        // 记录 p 串中有多少种字母
        int a = 0;
        for (int i = 0; i < m; i++) {
            char c = p.charAt(i);
            if (cnt[c - 'a'] == 0) {
                a++;
            }
            cnt[c - 'a']++;
        }
        // b 用来表示滑动窗口中与 p 串中词频相同的个数
        // a == b 就表示窗口中的串是答案
        int b = 0;
        int l = 0, r = 0;
        while (r < n) {
            char c = s.charAt(r);
            int index = c - 'a';
            cnt[index]--;
            if (cnt[index] == 0) {
                b++;
            }
            // 窗口太长
            if (r - l + 1 > m) {
                c = s.charAt(l);
                index = c - 'a';
                cnt[index]++;
                if (cnt[index] == 1) {
                    b--;
                }
                l++;
            }
            if (a == b) {
                list.add(l);
            }
            r++;
        }
        return list;
    }
}
