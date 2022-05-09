package com.leetcode.backtracing;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * 1、path 的 size 需要是 n
 * 2、集合中元素不能重复取，但是能乱序取，也就是先取后面的，再取前面的；不需要 startIndex
 * 3、取得是叶子节点
 * <p>
 * 这里是同一树枝不能重复取了，同一树层不存在重复元素
 *
 * @author fdn
 * @since 2022-04-25 11:09
 */
public class Permutation {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int n;
    int[] used;

    public List<List<Integer>> permute(int[] nums) {
        n = nums.length;
        used = new int[n];
        backTracing(nums, used);
        return result;
    }

    private void backTracing(int[] nums, int[] used) {
        // 终止和收集条件一致
        if (path.size() == n) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (used[i] == 1) {
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
         * 46. 全排列
         * <p>
         * 全排列取得应该还是树枝，到叶子节点之后取树枝
         * <p>
         * 元素不能重复取，还是得需要startIndex来记录位置，
         * 但是有了startIndex，元素就只能从前往后取，因为是全排列，所以元素是可以先取后面再取前面的。startIndex不好控制了
         * 用Set去重怎么样。
         * 还是用used数组来保证元素不被重复选取
         *
         * @param nums
         * @return
         */
        List<List<Integer>> result;
        List<Integer> path;
        int[] used;

        public List<List<Integer>> permute(int[] nums) {
            result = new ArrayList<>();
            path = new ArrayList<>();
            used = new int[nums.length];
            backTracing(nums, used);
            return result;
        }

        private void backTracing(int[] nums, int[] used) {
            // 收集结果
            if (path.size() == nums.length) {
                result.add(new ArrayList<>(path));
                return;
            }
            // 每个树枝都要遍历全部的元素，不需要剪枝吧
            // 每次遍历都是从 i==0 开始，配合used数组，实现既不重复取，又做到元素选取的顺序可以随意
            for (int i = 0; i < nums.length; i++) {
                // 表示这个元素在这一树支上使用过，其他人碰到了就不要选
                if (used[i] == 1) {
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
