package com.leetcode.backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 * <p>
 * 终止条件和收集条件一致，path.size() == n
 * 含有相同元素，同一树枝可以选取相同元素了（但不是同一个）
 * 同一树层不能选相同的了
 * 但是元素选取顺序可以乱，可以先选后面的元素再选前面的元素，不需要 startIndex
 * 排序与否，无所谓吧
 *
 * @author fdn
 * @since 2022-04-25 11:27
 */
public class Permutation2 {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int n;
    int[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        n = nums.length;
        used = new int[n];
        backTracing(nums, used);
        return result;
    }

    private void backTracing(int[] nums, int[] used) {
        // 收集 & 终止
        if (path.size() == n) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; i++) {
            // 同一数层【相等】的元素不能取；同一树枝【相同】的元素不能取
            if ((i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0) || used[i] == 1) {
                continue;
            }
            used[i] = 1;
            path.add(nums[i]);
            backTracing(nums, used);
            path.remove(path.size() - 1);
            used[i] = 0;
        }
    }

    class Test {
        /**
         * 47. 全排列 II
         * <p>
         * 肯定用不上 startIndex ，因为元素可以乱序取
         * 元素不能重复取，可以用 used 数组来过滤
         * 相同元素同一层也不能取
         *
         * @param nums
         * @return
         */
        List<List<Integer>> result;
        List<Integer> path;
        int[] used;

        public List<List<Integer>> permuteUnique(int[] nums) {
            result = new ArrayList<>();
            path = new ArrayList<>();
            used = new int[nums.length];
            // 先排序，方便后面判断是否使用过相等但不是同一个元素
            Arrays.sort(nums);
            backTracing(nums, used);
            return result;
        }

        private void backTracing(int[] nums, int[] used) {
            // 收集
            if (path.size() == nums.length) {
                result.add(new ArrayList<>(path));
                return;
            }
            // 终止条件写在了收集结果里面

            // 单次遍历
            for (int i = 0; i < nums.length; i++) {
                // 两种情况不能取，一是该元素已经在这一【树枝】使用过，
                // 一是前一个元素与自己相同，且前一个元素在这一【数层】使用过
                if ((i > 0 && nums[i] == nums[i - 1]) && used[i - 1] == 0 || used[i] == 1) {
                    continue;
                }
                path.add(nums[i]);
                used[i] = 1;
                backTracing(nums, used);
                used[i] = 0;
                path.remove(path.size() - 1);
            }
        }
    }
}
