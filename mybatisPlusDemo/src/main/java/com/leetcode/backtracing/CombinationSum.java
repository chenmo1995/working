package com.leetcode.backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 * <p>
 * 数组中元素可以取任意次，那么每次都应该从第一个数去尝试取。
 * 这里可以做一个剪枝，将数组排个序，当所选数的和超出 target 时，后面也就不用取了
 *
 * @author fdn
 * @since 2022-04-24 16:43
 */
public class CombinationSum {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backTracing(candidates, target, 0, 0);
        return result;
    }

    private void backTracing(int[] candidates, int target, int sum, int startIndex) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
            sum += candidates[i];
            path.add(candidates[i]);
            // 可以重复取当前数字，但是不能回过头来取前面的数字
            backTracing(candidates, target, sum, i);
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }


    class Test {
        /**
         * 39. 组合总和
         *
         * @param candidates
         * @param target
         * @return
         */
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            backTracing(0, target, candidates);
            return result;
        }

        private void backTracing(int sum, int target, int[] candidates) {
            if (sum == target) {
                List<Integer> temp = new ArrayList<>(path);
                path.sort((a, b) -> a - b);
                if (!result.contains(path)) {
                    result.add(new ArrayList<>(path));
                }
                path = temp;
                return;
            }
            for (int i = 0; i < candidates.length && sum + candidates[i] <= target; i++) {
                path.add(candidates[i]);
                sum += candidates[i];
                backTracing(sum, target, candidates);
                sum -= candidates[i];
                path.remove(path.size() - 1);
            }
        }

        /**
         * 这个方法没有排序，也没有去重，效率高不少
         * 但是还是有更好的方法，比如最上面的；
         *
         * @param candidates
         * @param target
         * @return
         */
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            backTracing2(0, target, candidates, 0);
            return result;
        }

        private void backTracing2(int sum, int target, int[] candidates, int startIndex) {
            if (sum > target) {
                return;
            }
            if (sum == target) {
                result.add(new ArrayList<>(path));
                return;
            }
            // && sum + candidates[i] <= target 这个for循环的判断条件要谨慎
            // 一方面是为了发现 sum + candidates[i] > target 就终止本次for循环，不必等到下一层递归通过 sum > target 来判断
            // 但是另一方面，这个条件的使用需要数组 candidates 是顺序排列的状态，如果不是，提前终止的循环会导致后面符合条件
            // 的搜索结果也没有被搜索到
            for (int i = startIndex; i < candidates.length; i++) {
                path.add(candidates[i]);
                sum += candidates[i];
                // 不用i+1了，表示可以重复读取当前的数
                backTracing2(sum, target, candidates, i);
                sum -= candidates[i];
                path.remove(path.size() - 1);
            }
        }
    }
}
