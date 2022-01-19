package com.leetcode.newyear.january;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author fdn
 * @since 2022-01-09 19:56
 */
public class Test0109 {

    /**
     * @param releaseTimes
     * @param keysPressed
     * @return
     */
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        Map<Character, Integer> map = new HashMap<>(16);
        // { 字母，时间 }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1] == 0 ? b[0] - a[0] : b[1] - a[1]);
        map.put(keysPressed.charAt(0), releaseTimes[0]);
        pq.offer(new int[]{keysPressed.charAt(0) - 'a', releaseTimes[0]});
        for (int i = 1; i < keysPressed.length(); i++) {
            char c = keysPressed.charAt(i);
            map.put(c, map.getOrDefault(c, 0) > (releaseTimes[i] - releaseTimes[i - 1]) ? map.get(c) : (releaseTimes[i] - releaseTimes[i - 1]));
            pq.offer(new int[]{c - 'a', map.get(c)});
        }

        return (char) (pq.peek()[0] + 'a');
    }
}
