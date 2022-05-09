package com.leetcode.newyear.february;

import java.util.Arrays;

/**
 * @author fdn
 * @since 2022-02-09 16:49
 */
public class Test0202 {

    /**
     * 2000. 反转单词前缀
     *
     * @param word
     * @param ch
     * @return
     */
    public String reversePrefix(String word, char ch) {
        char[] chars = word.toCharArray();
        int index = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ch) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return word;
        }
        int l = 0, r = index;
        while (l < r) {
            char temp = chars[r];
            chars[r] = chars[l];
            chars[l] = temp;
            l++;
            r--;
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        char[] chars = {'a','b'};
        System.out.println(String.valueOf(chars));
    }
}
