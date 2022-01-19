package com.leetcode.newyear.january;

import java.util.HashSet;
import java.util.Set;

/**
 * @author fdn
 * @since 2022-01-19 10:49
 */
public class Test0119 {

    /**
     * 219. 存在重复元素 II
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;
        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < n && i <= k; i++) {
            if (numSet.contains(nums[i])) {
                return true;
            } else {
                numSet.add(nums[i]);
            }
        }
        for (int i = k + 1; i < n; i++) {
            numSet.remove(nums[i - k - 1]);
            if (numSet.contains(nums[i])) {
                return true;
            } else {
                numSet.add(nums[i]);
            }
        }
        return false;
    }
}
