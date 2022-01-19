package com.leetcode.december;

import java.util.Arrays;

/**
 * @author fdn
 * @since 2021-12-29 16:00
 */
public class Test1229 {

    /**
     * 1995. 统计特殊四元组
     * 考虑用回溯来全员遍历
     * 元素从前往后取，不能重复取，可以取值相等的
     * <p>
     * 艹，不能打乱顺序，别剪枝了
     *
     * @param nums
     * @return
     */
    int result;
    int count;

    public int countQuadruplets(int[] nums) {
        backTracing(nums, 0, 0);
        return result;
    }

    private void backTracing(int[] nums, int startIndex, int sum) {
        // 收集
        if (count == 4 && sum == 2 * nums[startIndex - 1]) {
            result++;
        }
        // 终止条件，当 count < 4 且 sum >= nums[nums.length-1] 时，数组剩下的元素就没有满足条件的了；
        // 不能排序，这个剪枝也就不能使用了

        if (count + (nums.length - startIndex) < 4) {
            return;
        }

        // 单层逻辑
        for (int i = startIndex; i < nums.length && count < 4; i++) {
            count++;
            sum += nums[i];
            backTracing(nums, i + 1, sum);
            sum -= nums[i];
            count--;
        }
    }

    public static void main(String[] args) {
        Test1229 test1229 = new Test1229();
        test1229.countQuadruplets(new int[]{1, 2, 3, 6});
    }
}
