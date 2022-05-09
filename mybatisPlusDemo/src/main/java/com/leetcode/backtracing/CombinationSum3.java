package com.leetcode.backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III
 * @author fdn
 * @since 2022-04-24 15:57
 */
public class CombinationSum3 {

    List<List<Integer>> result;
    List<Integer> path;

    public List<List<Integer>> combinationSum3(int k, int n) {
        result = new ArrayList<>();
        path = new ArrayList<>();

        backTracing(n, k, 1, 0);
        return result;
    }

    private void backTracing(int n, int k, int startIndex, int sum) {
        if (sum == n && path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 和大了      数多了
        if (sum > n || path.size() > k) {
            return;
        }
        // 剩余的数少了
        if (path.size() + (9 - startIndex + 1) < k) {
            return;
        }
        // 剩余的数小了
        if (sum + (9 + startIndex) * (9 - startIndex + 1) / 2 < n) {
            return;
        }
        for (int i = startIndex; i <= 9; i++) {
            sum += i;
            path.add(i);
            backTracing(n, k, i + 1, sum);
            sum -= i;
            path.remove(path.size() - 1);
        }
    }


    class Test {
        /**
         * 216. 组合总和 III
         *
         * @param k
         * @param n
         * @return
         */
        List<List<Integer>> result;
        List<Integer> path;

        public List<List<Integer>> combinationSum3(int k, int n) {

            backTracing2(n, k, 1);
            return result;
        }

        private void backTracing2(int n, int k, int startIndex) {
            // 剪枝
            // 1、长度大于k；2、长度小于k，剩余个数小于k-size
            // 3、和大于n；  4、和加剩余的和，小于n
            // 这一步剪枝可以用，但是每次回溯都要求和，非常耗费性能，建议在回溯里面把上一次的和加上去。
            // 然后在处理节点的时候 sum+i，再在回溯撤销处理结果的那一步中把i弹出path的同时，在sum中也减去i
            if (path.size() > k || (path.size() < k && (9 - startIndex + 1) < k - path.size())) {
                return;
            }
            if (path.stream().reduce(0, (a, b) -> a + b) > n || (startIndex + 9) * (9 - startIndex + 1) / 2 + path.stream().reduce(0, (a, b) -> a + b) < n) {
                return;
            }
            if (path.stream().reduce(0, (a, b) -> a + b) == n && path.size() == k) {
                result.add(new ArrayList<>(path));
                return;
            }
            for (int i = startIndex; i <= 9; i++) {
                path.add(i);
                backTracing2(n, k, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
}