package com.leetcode.backtracing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合
 *
 * @author fdn
 * @since 2022-04-24 15:42
 */
public class Combinations {

    List<List<Integer>> result;
    LinkedList<Integer> path;

    public List<List<Integer>> combine(int n, int k) {
        result = new ArrayList<>();
        path = new LinkedList<>();
        backTracing(n, k, 1);
        return result;
    }

    private void backTracing(int n, int k, int startIndex) {
        // 剪枝，剩下的元素加上 path 中的元素个数小于 k，就不用继续组合了，肯定是不符合条件的
        if (path.size() + n - startIndex + 1 < k) {
            return;
        }

        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            backTracing(n, k, i + 1);
            path.removeLast();
        }
    }


    class Test {
        /**
         * 77. 组合
         *
         * @param n
         * @param k
         * @return
         */
        // 用来存放符合条件的结果的集合
        List<List<Integer>> result = new ArrayList<>();
        // 用来存放 单个 符合条件的结果
        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> combine(int n, int k) {

            backTracing(n, k, 1);

            return result;
        }

        private void backTracing(int n, int k, int startIndex) {
            // 剪枝：path 长度加上区间 [startIndex, n] 的长度小于 k，不可能构造出长度为 k 的 path
            if (path.size() + (n - startIndex + 1) < k) {
                return;
            }
            if (path.size() == k) {
                result.add(new ArrayList<>(path));
                // 这一步一定不行写成 result.add(path),因为后面会对path.remove，会改变path
                // 对应的，result 中 add 的 path 也就改变了
                return;
            }
            for (int i = startIndex; i <= n; i++) {
                path.add(i);
                backTracing(n, k, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }
}