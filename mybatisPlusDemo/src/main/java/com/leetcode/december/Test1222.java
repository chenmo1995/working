package com.leetcode.december;

/**
 * kmp
 *
 * @author fdn
 * @since 2021-12-22 23:28
 */
public class Test1222 {

    /**
     * 686. 重复叠加字符串匹配
     *
     * @param a
     * @param b
     * @return
     */
    public int repeatedStringMatch(String a, String b) {
        int num = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < b.length()) {
            sb.append(a);
            num++;
        }
        if (sb.indexOf(b) != -1) {
            return num;
        }
        sb.append(a);
        num++;
        if (sb.indexOf(b) != -1) {
            return num;
        }
        return -1;
    }

    public int repeatedStringMatch2(String a, String b) {
        int num = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < b.length()) {
            sb.append(a);
            num++;
        }
        if (sb.indexOf(b) != -1) {
            return num;
        }
        sb.append(a);
        num++;
        if (sb.indexOf(b) != -1) {
            return num;
        }
        return -1;
    }

    /**
     * 28. 实现 strStr()
     * KMP
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        // 构建next数组，表示最大相等前后缀长度
        int[] next = new int[needle.length()];
        getNext(next, needle);
        // 开始匹配文本串S（源串）和模式串P（匹配串），遍历文本串
        // next数组里记录的起始位置为0
        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            // 不匹配
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                // j寻找之前匹配的位置，也就是前一位在的next数组中的值。
                // 直到 j==0 的时候，模式串无法回溯了，文本串就需要往后移一位（for循环里的i++）
                j = next[j - 1];
            }
            // 匹配，模式串（j++）和文本串都往后位移一位（for循环里的i++）
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            // 当完全匹配成功了(上一次匹配的是模式串的最后一位，j++之后 j==needle.length())，
            // 退出循环，返回最开始出现的位置（i减去模式串的长度，下一位就是模式串出现的起始位置）
            if (j == needle.length()) {
                return i - (needle.length() - 1);
            }
        }
        return -1;
    }

    private void getNext(int[] next, String needle) {
        // j表示前缀的最后一位，i表示后缀的最后一位。P串第一个字符无前后缀，从第二个字符开始
        // j因为是前缀的最后一位，所以他还表示着 i 往前所有子串的最大相等前后缀长度
        next[0] = 0;

        for (int j = 0, i = 1; i < needle.length(); i++) {
            // needle[j] != needle[i]
            while (j > 0 && needle.charAt(j) != needle.charAt(i)) {
                // 为满足相等前后缀条件， 前缀最后一位往前回溯
                j = next[j - 1];
            }
            // 如果needle[j] == needle[i],显然可以继续往下匹配，i 和 j 同时向后移动，j++，i在for循环里面++
            if (needle.charAt(j) == needle.charAt(i)) {
                j++;
            }
            // 更新next数组，（因为j的值就表示i包括i往前的子串的最大相等前后缀长度）结束本次循环，i++后继续下一次循环
            next[i] = j;
        }
    }

    public static void main(String[] args) {
        "".indexOf("");
    }
}
