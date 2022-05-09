package com.leetcode.newyear.january;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fdn
 * @since 2022-01-25 09:57
 */
public class Test0125 {
    public int numberOfMatches(int n) {
        int count = 0;
        while (n > 1) {
            count += n / 2;
            n = n % 2 == 0 ? n / 2 : n / 2 + 1;
        }
        return count;
    }

    public static void main(String[] args) {
        Collections.sort(new ArrayList<Integer>());
    }
}
