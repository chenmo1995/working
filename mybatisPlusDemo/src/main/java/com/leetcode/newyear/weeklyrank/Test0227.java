package com.leetcode.newyear.weeklyrank;

/**
 * @author fdn
 * @since 2022-02-27 10:31
 */
public class Test0227 {
    /**
     * 6008. 统计包含给定前缀的字符串
     *
     * @param words
     * @param pref
     * @return
     */
    public int prefixCount(String[] words, String pref) {
        int cnt = 0;
        for (String word : words) {
            if (isStart(pref, word)) {
                cnt++;
            }
        }
        return cnt;
    }

    private boolean isStart(String pref, String word) {
        int n = word.length();
        int m = pref.length();
        if (m > n) {
            return false;
        }
        for (int i = 0; i < m; i++) {
            if (word.charAt(i) != pref.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 6009. 使两字符串互为字母异位词的最少步骤数
     *
     * @param s
     * @param t
     * @return
     */
    public int minSteps(String s, String t) {
        int[] cntS = new int[26];
        int[] cntT = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cntS[c - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            cntT[c - 'a']++;
        }
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += Math.abs(cntS[i] - cntT[i]);
        }
        return sum;
    }

    /**
     * 6010. 完成旅途的最少时间
     * 感觉是二分，大于等于 t ，就能完成；小于 t 就不能完成
     *
     * @param time
     * @param totalTrips
     * @return
     */
    int[] time;
    int totalTrips;

    public long minimumTime(int[] time, int totalTrips) {
        this.time = time;
        this.totalTrips = totalTrips;
        long r = (long) 1e14;
        long l = 0;
        while (l < r) {
            long mid = (r - l >> 1) + l;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(long mid) {
        long cnt = 0;
        for (int num : time) {
            cnt += mid / num;
        }
        return cnt >= totalTrips;
    }

    /**
     * 6011. 完成比赛的最少时间
     *
     * @param tires
     * @param changeTime
     * @param numLaps
     * @return
     */
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        return 0;
    }

    public static void main(String[] args) {
        Test0227 test0227 = new Test0227();
        test0227.minimumTime(new int[]{1000}, 10000000);
    }
}
