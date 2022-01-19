package com.leetcode.newyear.january;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author fdn
 * @since 2022-01-07 09:46
 */
public class Test0107 {

    /**
     * 1614. 括号的最大嵌套深度
     *
     * @param s
     * @return
     */
    public int maxDepth(String s) {
        int maxDepth = 0;
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                deque.offerLast(c);
            } else if (c == ')') {
                maxDepth = Math.max(maxDepth, deque.size());
                deque.pollLast();
            }
        }
        return maxDepth;
    }
}
