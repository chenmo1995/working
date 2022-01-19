package com.leetcode.december;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fdn
 * @since 2021-12-26 12:41
 */
public class Test1226 {

    /**
     * @param text
     * @param first
     * @param second
     * @return
     */
    public String[] findOcurrences(String text, String first, String second) {
        String[] s = text.split(" ");
        String[] temp = new String[s.length];
        int index = 0;
        for (int i = 0; i < s.length - 2; i++) {
            if (s[i].equals(first)) {
                if (s[i + 1].equals(second)) {
                    temp[index++] = s[i + 2];
                }
            }
        }
        return Arrays.copyOf(temp, index);
    }


}
