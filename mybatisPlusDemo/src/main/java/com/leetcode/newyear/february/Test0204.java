package com.leetcode.newyear.february;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author fdn
 * @since 2022-02-09 17:00
 */
public class Test0204 {

    /**
     * 1725. 可以形成最大正方形的矩形数目
     *
     * @param rectangles
     * @return
     */
    public int countGoodRectangles(int[][] rectangles) {
        // <正方形边长，个数>
        Map<Integer, Integer> map = new HashMap<>(16);
        int maxLen = 0;
        for (int[] rectangle : rectangles) {
            int len = Math.min(rectangle[0], rectangle[1]);
            maxLen = Math.max(maxLen, len);
            map.put(len, map.getOrDefault(len, 0) + 1);
        }
        return map.get(maxLen);
    }
}
