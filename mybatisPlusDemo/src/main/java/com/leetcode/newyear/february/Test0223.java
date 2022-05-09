package com.leetcode.newyear.february;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author fdn
 * @since 2022-02-23 22:48
 */
public class Test0223 {

    /**
     * 917. 仅仅反转字母
     *
     * @param s
     * @return
     */
    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        Deque<Character> str = new ArrayDeque<>();
        Deque<Character> cha = new ArrayDeque<>();
        Deque<Character> total = new ArrayDeque<>();
        for (char aChar : chars) {
            if ((aChar >= 'A' && aChar <= 'Z') || (aChar >= 'a' && aChar <= 'z')) {
                total.offerLast(aChar);
                str.offerLast(aChar);
            } else {
                cha.offerLast(aChar);
                total.offerLast('\\');
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!total.isEmpty()) {
            Character character = total.pollFirst();
            if (character != '\\') {
                sb.append(str.pollLast());
            } else {
                sb.append(cha.pollFirst());
            }
        }
        return sb.toString();
    }

    public String reverseOnlyLetters2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int l = 0, r = n - 1;
        while (l < r) {
            while (l < r && !Character.isLetter(chars[l])) {
                l++;
            }
            while (l < r && !Character.isLetter(chars[r])) {
                r--;
            }
            char temp = chars[r];
            chars[r] = chars[l];
            chars[l] = temp;
            r--;
            l++;
        }
        return String.valueOf(chars);
    }
}
