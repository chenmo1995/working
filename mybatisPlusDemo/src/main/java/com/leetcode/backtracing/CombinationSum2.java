package com.leetcode.backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 *
 * @author fdn
 * @since 2022-04-24 17:12
 */
public class CombinationSum2 {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int[] used;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        used = new int[candidates.length];
        backTracing(candidates, target, 0, 0, used);
        return result;
    }

    private void backTracing(int[] candidates, int target, int sum, int startIndex, int[] used) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
            // used[i - 1] == 1，说明同一树支candidates[i - 1]使用过；是我们主动令 used[i] == 1，同一树枝的遍历中可以重复使用
            // used[i - 1] == 0，说明同一树层candidates[i - 1]使用过；默认值，统一树层如果没动的话，肯定再上一层 for 循环就使用过
            if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1] == 0) {
                continue;
            }
            sum += candidates[i];
            path.add(candidates[i]);
            used[i] = 1;
            backTracing(candidates, target, sum, i + 1, used);
            sum -= candidates[i];
            path.remove(path.size() - 1);
            used[i] = 0;
        }
    }


    class Test {
        /**
         * 40. 组合总和 II
         *
         * @param candidates
         * @param target
         * @return
         */
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        // 记录前面一个数是在同一数层还是在同一树枝
        int[] used;

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            used = new int[candidates.length];
            // 必须排序，不然后面用used去重实现不了
            Arrays.sort(candidates);
            backTracing(0, candidates, target, 0, used);
            return result;
        }

        private void backTracing(int sum, int[] candidates, int target, int startIndex, int[] used) {
            if (sum == target) {
                result.add(new ArrayList<>(path));
                return;
            }
            // && sum + candidates[i] <= target; 这个剪枝一般是在数组从小到大排序了之后才可以使用的
            for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
                if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1] == 0) {
                    continue;
                }
                // used[i - 1] == 1，说明同一树支candidates[i - 1]使用过
                // used[i - 1] == 0，说明同一树层candidates[i - 1]使用过
                path.add(candidates[i]);
                sum += candidates[i];
                used[i] = 1;
                // 带着1进入递归下一层
                backTracing(sum, candidates, target, i + 1, used);
                used[i] = 0;
                // 带着0进入for循环的下一层
                sum -= candidates[i];
                path.remove(path.size() - 1);
            }

        }
    }
}
