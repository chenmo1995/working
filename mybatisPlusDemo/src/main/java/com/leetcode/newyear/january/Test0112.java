package com.leetcode.newyear.january;

import java.util.Arrays;

/**
 * @author fdn
 * @since 2022-01-12 10:00
 */
public class Test0112 {

    /**
     * 334. 递增的三元子序列
     * 是不是用二分呢，某个元素的左侧有比他小的数，右侧有比他大的数，就算是满足条件了
     * <p>
     * 或者动归呢    dp[i][0] 表示第 i 个元素左边比他小的个数，dp[i][1] 表示第 i 个元素右边比他大的个数
     * 动归不行，递推公式写不出来；或者说后一个状态不能完全由前面的状态推导出来
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet2(int[] nums) {
        // 暴力
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            boolean flag1 = false;
            boolean flag2 = false;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    flag1 = true;
                }
            }
            for (int j = i + 1; j < n; j++) {
                if (nums[i] < nums[j]) {
                    flag2 = true;
                }
            }
            if (flag1 && flag2) {
                return true;
            }
        }
        return false;
    }

    /**
     * 334. 递增的三元子序列
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        // dp[i] 表示前 i 个数中的最小值
        int[] leftMin = new int[n];
        int[] rightMax = new int[n];
        leftMin[0] = nums[0];
        rightMax[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > leftMin[i] && nums[i] < rightMax[i]) {
                return true;
            }
        }
        return false;
    }
}
