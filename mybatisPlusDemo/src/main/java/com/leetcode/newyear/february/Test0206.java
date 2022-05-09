package com.leetcode.newyear.february;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fdn
 * @since 2022-02-10 21:38
 */
public class Test0206 {
    /**
     * 1748. 唯一元素的和
     *
     * @param nums
     * @return
     */
    public int sumOfUnique(int[] nums) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer integer : map.keySet()) {
            if (map.get(integer) == 1) {
                sum += integer;
            }
        }
        return sum;
    }
}
