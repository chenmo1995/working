package com.leetcode.newyear.interview21.tooffer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author fdn
 * @since 2022-03-21 11:14
 */
public class Test0321 {
    /**
     * 剑指 Offer 03. 数组中重复的数字
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            } else {
                set.add(num);
            }
        }
        return -1;
    }

    public int[] reversePrint(ListNode head) {
        int[] ans = new int[10000];
        int index = 0;
        while (head != null) {
            ans[index++] = head.val;
            head = head.next;
        }
        int[] result = new int[index];
        for (int i = 0; i < result.length; i++) {
            result[i] = ans[--index];
        }
        return result;
    }

    /**
     * 32. 最长有效括号
     * 首先需要以 '(' 开头，以 ')' 结尾，
     * 子串中，从前往后遍历，')' 的数量时刻小于等于 '(' 等数量
     * '(' ')' 数量相等
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        // dp[i] 表示以 s[i] 为结尾的最长有效括号的长度
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 0;
        int max = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
            } else {
                // s[i] == ')'
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i == 1 ? 0 : dp[i - 2]) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    // s[i]、s[i-1] == ')'
                    // [] + ( + [] + )，将两个有效括号连接起来了
                    dp[i] = ((i - dp[i - 1]) > 2 ? dp[i - dp[i - 1] - 2] : 0) + (1 + dp[i - 1] + 1);
                } else {
                    // 不存在其他情况了，如果 [] + ) + [] + )，dp[i] 就为 0
                    dp[i] = 0;
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
