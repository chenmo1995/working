package com.leetcode.newyear.february;

import java.util.PriorityQueue;

/**
 * @author fdn
 * @since 2022-02-07 09:11
 */
public class Test0207 {

    /**
     * 1405. 最长快乐字符串
     * 这种问题，只能是回溯吧
     * <p>
     * 分析题意，如果 a b c 的个数一样，那么很轻松的就实现了快乐字符串，并且包含了全部个数的 a b c
     * <p>
     * 模拟一个过程，谁最大就用谁，同时记录连续使用的次数，达到 2 了，下一次就使用不同的字符。
     * 跳出循环的条件就是最大个数的字符使用次数已经到了 2 ，同时另外两个字符的剩余个数都是 0；或者三个字符的剩余个数都是 0.
     *
     * @param a
     * @param b
     * @param c
     * @return
     */


    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        if (a > 0) {
            q.add(new int[]{0, a});
        }
        if (b > 0) {
            q.add(new int[]{1, b});
        }
        if (c > 0) {
            q.add(new int[]{2, c});
        }
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int n = sb.length();
            if (n >= 2 && sb.charAt(n - 1) - 'a' == cur[0] && sb.charAt(n - 2) - 'a' == cur[0]) {
                if (q.isEmpty()) break;
                int[] next = q.poll();
                sb.append((char) (next[0] + 'a'));
                if (--next[1] != 0) q.add(next);
                q.add(cur);
            } else {
                sb.append((char) (cur[0] + 'a'));
                if (--cur[1] != 0) q.add(cur);
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Test0207 test0207 = new Test0207();
        test0207.longestDiverseString(1, 1, 7);
    }
}
