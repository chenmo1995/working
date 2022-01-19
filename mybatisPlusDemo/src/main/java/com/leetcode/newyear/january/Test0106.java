package com.leetcode.newyear.january;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author fdn
 * @since 2022-01-06 09:52
 */
public class Test0106 {

    /**
     * 71. 简化路径
     * 可以考虑用栈来存储有效目录
     * 碰见目录，加入栈，碰见 "." 表示当前目录，可以丢弃；碰见 ".." 表示上一级目录，对栈进行弹出操作
     * 如何处理 "/" 呢，可以先将字符串以 "/" 分割
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        String[] strings = path.split("/");
        Deque<String> deque = new ArrayDeque<>();
        deque.offerLast("/");
        for (String string : strings) {
            if ("".equals(string) || ".".equals(string)) {
            } else if ("..".equals(string)) {
                if (deque.size() > 1) {
                    deque.pollLast();
                }
            } else {
                deque.offerLast(string);
            }
        }
        StringBuilder result = new StringBuilder();
        if (deque.size() == 1) {
            return "/";
        }
        result.append(deque.pollFirst());
        while (!deque.isEmpty()) {
            result.append(deque.pollFirst());
            result.append("/");
        }
        return result.substring(0, result.length() - 1);
    }

    public static void main(String[] args) {
        String a = "/../";
        String[] split = a.split("/");
        for (String s : split) {
            System.out.println(s);
        }
        System.out.println(split.length);
    }
}
