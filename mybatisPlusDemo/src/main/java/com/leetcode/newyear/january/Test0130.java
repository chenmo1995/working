package com.leetcode.newyear.january;

import java.util.*;

/**
 * @author fdn
 * @since 2022-02-07 15:52
 */
public class Test0130 {

    /**
     * 884. 两句话中的不常见单词
     *
     * @param s1
     * @param s2
     * @return
     */
    public String[] uncommonFromSentences(String s1, String s2) {
        String s = s1 + " " + s2;
        String[] split = s.split(" ");

        Map<String, Integer> map = new HashMap<>(16);
        for (String s3 : split) {
            map.put(s3, map.getOrDefault(s3, 0) + 1);
        }
        List<String> list = new ArrayList<>();
        for (String s3 : map.keySet()) {
            if (map.get(s3) == 1) {
                list.add(s3);
            }
        }
        return list.toArray(new String[list.size()]);
    }
}
