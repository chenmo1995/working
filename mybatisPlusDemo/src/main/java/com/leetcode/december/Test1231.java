package com.leetcode.december;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fdn
 * @since 2021-12-31 10:20
 */
public class Test1231 {

    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        for (int i = 2; i < (int) Math.sqrt(num) + 1; i++) {
            if (num % i == 0) {
                set.add(i);
                set.add(num / i);
            }
        }
        set.add(1);
        for (Integer element : set) {
            sum += element;
        }
        return num == sum;
    }

    /**
     * 28. 实现 strStr()
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        if (n == 0) {
            return 0;
        }
        // 构建 next 数组。用来表示最大相等前后缀的长度；在 needle 的基础上构建
        int[] next = new int[n];
        getNext(next, needle);
        // 开始匹配 ；在 haystack 的基础上匹配
        // i 表示文本串中的位置，j 表示模式串中的位置
        for (int i = 0, j = 0; i < m; i++) {
            // 不匹配
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                // 很据 next 数组回溯
                j = next[j - 1];
            }
            // 匹配
            if (haystack.charAt(i) != needle.charAt(j)) {
                j++;
            }
            // 匹配到模式串的最后一位且成功了,返回模式串在文本串中首次出现的位置
            if (j == n) {
                return i - (n - 1);
            }
        }
        // 没匹配成功，但是文本串已经遍历结束了
        return -1;
    }

    private void getNext(int[] next, String needle) {
        // i 表示后缀的最后一位，j 表示前缀的最后一位，匹配串P第一个字符无前后缀，从第二个字符开始
        next[0] = 0;
        for (int i = 1, j = 0; i < needle.length(); i++) {
            // 先处理前后缀不相等的情况
            while (j > 0 && needle.charAt(j) != needle.charAt(i)) {
                // 向前回溯
                j = next[j - 1];
            }
            // 前后缀相等的情况，j 和 i 都向后移动
            j++;
            // 更新 next 数组
            next[i] = j;
        }
    }

}
