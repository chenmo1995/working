package com.leetcode.backtracing;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author fdn
 * @since 2022-04-26 16:58
 */
public class Test {

    /**
     * 698. 划分为k个相等的子集
     * 首先应该求和，sum/k 定为每个子集的和；
     * 元素不能重复取，需要 startIndex 来控制
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int avg = sum / k;
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[n - 1] > avg) {
            return false;
        }
        int[] used = new int[n];
        return backTracing(nums, n - 1, 0, avg, k, used);
    }

    private boolean backTracing(int[] nums, int startIndex, int sum, int target, int k, int[] used) {
        // 剩最后一个集合了，直接返回 true，因为前面 k - 1 个集合都凑出来了，剩余的数必能组成最后一个集合
        if (k == 1) {
            return true;
        }
        // 一个子集完成了，进行下一个
        if (sum == target) {
            return backTracing(nums, nums.length - 1, 0, target, k - 1, used);
        }
        // 单层逻辑
        for (int i = startIndex; i >= 0; i--) {
            // 元素使用过 || 和太大了
            if (used[i] == 1 || sum + nums[i] > target) {
                continue;
            }
            sum += nums[i];
            used[i] = 1;
            if (backTracing(nums, i - 1, sum, target, k, used)) {
                return true;
            }
            used[i] = 0;
            sum -= nums[i];
            // 回溯之后如果下个元素跟回溯出去的元素相等，直接跳过，去找到不相等的元素再尝试加入
            while (i > 0 && nums[i - 1] == nums[i]) {
                i--;
            }
        }
        return false;
    }
}
