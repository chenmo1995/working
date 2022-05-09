package com.leetcode.backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. 子集 II
 * 1、使用过的元素不能使用，应该用 startIndex 来控制
 * 2、取子集问题，取得是所有的节点，应该在每次递归中收集结果
 * 3、集合包含重复元素，显然是【树枝可以重复取，树层不能重复取】
 *
 * @author fdn
 * @since 2022-04-25 10:11
 */
public class SubSets2 {

    public static void main(String[] args) {
        SubSets2 test = new SubSets2();
        test.subsetsWithDup(new int[]{1,2,2});
    }
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int n;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        n = nums.length;
        Arrays.sort(nums);
        backTracing(nums, 0);
        return result;
    }

    private void backTracing(int[] nums, int startIndex) {
        // 先收集
        result.add(new ArrayList<>(path));
        // 判断终止条件
        if (startIndex == n) {
            return;
        }
        // 单层逻辑
        for (int i = startIndex; i < n; i++) {
            // 如果是同一树枝的元素相同，每次递归进来，都是满足 i == startIndex，
            // 同一树层的话，i 已经满足 i > startIndex 了
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backTracing(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    class Test {
        /**
         * 90. 子集 II
         * 同一树枝可以重复，同一树层不能重复
         *
         * @param nums
         * @return
         */
        List<List<Integer>> result;
        List<Integer> path;
        int[] used;

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            result = new ArrayList<>();
            path = new ArrayList<>();
            used = new int[nums.length];
            Arrays.sort(nums);
            backTracing(nums, 0, used);
            return result;
        }

        private void backTracing(int[] nums, int startIndex, int[] used) {
            // 子集，收集节点
            result.add(new ArrayList<>(path));

            // 终止条件，startIndex 大于等于 nums.length 就应该停止了
            if (startIndex >= nums.length) {
                return;
            }

            // 循环递归
            for (int i = startIndex; i < nums.length; i++) {
                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0) {
                    continue;
                }
                path.add(nums[i]);
                used[i] = 1;
                backTracing(nums, i + 1, used);
                used[i] = 0;
                path.remove(path.size() - 1);
            }

        }
    }

    class Test2 {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int n;
        int[] used;

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            n = nums.length;
            used = new int[n];
            Arrays.sort(nums);
            backTracing(nums, 0, used);
            return result;
        }

        private void backTracing(int[] nums, int startIndex, int[] used) {
            // 先收集
            result.add(new ArrayList<>(path));
            // 判断终止条件
            if (startIndex == n) {
                return;
            }
            // 单层逻辑
            for (int i = startIndex; i < n; i++) {
                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == 0) {
                    continue;
                }
                used[i] = 1;
                path.add(nums[i]);
                backTracing(nums, i + 1, used);
                path.remove(path.size() - 1);
                used[i] = 0;
            }
        }
    }
}
