package com.leetcode.backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * 元素依然不能重复取，需要 startIndex 来控制
 *
 * @author fdn
 * @since 2022-04-25 09:50
 */
public class SubSets {
    public static void main(String[] args) {
        SubSets test = new SubSets();
        test.subsets(new int[]{1, 2, 3});
    }

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int n;

    public List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        backTracing(nums, 0);
        return result;
    }

    private void backTracing(int[] nums, int startIndex) {
        // 终止条件
        // 如果是先收集再判断终止的话，可以用 startIndex == n 来判断
        if (startIndex > n) {
            return;
        }
        // 收集
        result.add(new ArrayList<>(path));
        // 单层逻辑
        for (int i = startIndex; i < n; i++) {
            path.add(nums[i]);
            backTracing(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    class Test {
        /**
         * 78. 子集
         * <p>
         * 元素可以选可以不选
         *
         * @param nums
         * @return
         */
        List<List<Integer>> result;
        List<Integer> path;

        public List<List<Integer>> subsets(int[] nums) {
            result = new ArrayList<>();
            path = new ArrayList<>();
            backTracing(nums, 0);
            return result;
        }

        private void backTracing(int[] nums, int startIndex) {
            // 注意这次是要收集节点，而不是收集路径了
            result.add(new ArrayList<>(path));

            // 终止条件，因为元素可选可不选，path不固定，所以可以用startIndex来判断，
            if (startIndex == nums.length) {
                //result.add(new ArrayList<>(path));
                return;
            }
            //「其实可以不需要加终止条件，因为startIndex >= nums.size()，本层for循环本来也结束了」。
            for (int i = startIndex; i < nums.length; i++) {
                path.add(nums[i]);
                backTracing(nums, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
}
