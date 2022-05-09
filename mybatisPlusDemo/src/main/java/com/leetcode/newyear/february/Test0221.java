package com.leetcode.newyear.february;

import java.util.*;

/**
 * @author fdn
 * @since 2022-02-21 09:41
 */
public class Test0221 {

    /**
     * 838. 推多米诺
     * BFS
     *
     * @param dominoes
     * @return
     */

    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        char[] chars = dominoes.toCharArray();
        // used[i] == 0，表示没有遍历过，其他值表示入队的时间
        int[] used = new int[n];
        // {index, 入队时间}
        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (chars[i] != '.') {
                deque.offerLast(new int[]{i, 1});
                used[i] = 1;
            }
        }
        while (!deque.isEmpty()) {
            int[] first = deque.pollFirst();
            int index = first[0];
            int time = first[1];
            if (chars[index] == 'R') {
                if (index == n - 1 || used[index + 1] != 0) {
                    continue;
                }
                chars[index + 1] = 'R';
                used[index + 1] = time + 1;
                deque.offerLast(new int[]{index + 1, time + 1});
            } else {
                if (index == 0 || chars[index - 1] == 'L') {
                    continue;
                }
                if (chars[index - 1] == '.') {
                    chars[index - 1] = 'L';
                    deque.offerLast(new int[]{index - 1, time + 1});
                    used[index - 1] = time + 1;
                } else if (chars[index - 1] == 'R' && time + 1 == used[index - 1]) {
                    // 后面一个条件表示 index - 1 是在这一次的遍历中倒向了 R。同时这一次遍历还希望他倒向 L
                    // 所以需要恢复成 '.'
                    chars[index - 1] = '.';
//                    deque.remove(new int[]{index - 1, time + 1});//不能加，时间复杂度太高
                }
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        System.out.println(new char[]{'a', '1', 'v'}.toString());//[C@7eda2dbb
        System.out.println(Arrays.toString(new char[]{'a', '1', 'v'}));//[C@7eda2dbb
        System.out.println(new String(new char[]{'a', '1', 'v'}));
    }
}
