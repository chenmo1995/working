package com.leetcode.newyear.february;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fdn
 * @since 2022-02-09 09:43
 */
public class Test0209 {
    /**
     * 2006. 差的绝对值为 K 的数对数目
     *
     * @param nums
     * @param k
     * @return
     */
    public int countKDifference(int[] nums, int k) {
        // 遍历的时候知道在此之前的元素中有没有满足 x = nums[i] + k，或者 x = nums[i] - k 的就可以了。
        // 所以考虑把之前元素出现的次数记录下来
        int count = 0;
        // <nums[i], count>
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int num : nums) {
            count += map.getOrDefault(num - k, 0) + map.getOrDefault(num + k, 0);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return count;
    }
}
