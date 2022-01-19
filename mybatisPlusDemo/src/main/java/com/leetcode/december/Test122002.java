package com.leetcode.december;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fdn
 * @since 2021-12-20 18:32
 */
public class Test122002 {

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
            path.remove(path.size()-1);
        }
    }
}
