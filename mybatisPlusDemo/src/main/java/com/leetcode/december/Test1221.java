package com.leetcode.december;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fdn
 * @since 2021-12-21 12:18
 */
public class Test1221 {

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

    public static void main(String[] args) {
        Test1221 test1221 = new Test1221();
        List<List<Integer>> lists = test1221.subsetsWithDup(new int[]{1, 1});
        System.out.println(lists);
    }

}
