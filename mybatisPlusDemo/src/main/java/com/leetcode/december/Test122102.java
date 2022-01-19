package com.leetcode.december;

import java.util.*;

/**
 * @author fdn
 * @since 2021-12-21 13:45
 */
public class Test122102 {

    /**
     * 491. 递增子序列
     * <p>
     * 初看能看到几个问题
     * 首先这也是子集问题，需要收集的是节点而不是树枝，同时节点中元素大于等于2才收集
     * 再者，元素不能重复取，所以需要 startIndex 来控制
     * 回溯的深度和宽度就是数组的长度了
     * <p>
     * 数组中还有相同元素，在同一树枝中是可以既取前面又取后面，但是在数层中就不行了，需要去重
     * <p>
     * 但是！！！ 因为不能对原数组进行排序，所以之前我们常用的去重逻辑是不能使用的
     * 这次的去重是本层前面如果使用了（不仅仅是前一个，而是本层之前所有的），就需要去重
     *
     * @param nums
     * @return
     */
    List<List<Integer>> result;
    List<Integer> path;

    public List<List<Integer>> findSubsequences(int[] nums) {
        result = new ArrayList<>();
        path = new ArrayList<>();
        backTracing(nums, 0);
        return result;
    }

    private void backTracing(int[] nums, int startIndex) {
        // 收集节点
        // 收集节点与收集路径的不同。收集节点是一边往深处递归一边收集
        // 收集路径得等到终止条件的时候收集
        if (path.size() >= 2) {
            result.add(new ArrayList<>(path));
        }
        // 终止条件，startIndex 走到最后即可终止，其实也可以不写
        if (startIndex >= nums.length) {
            return;
        }
        // 使用set对本层元素进行去重
        Set<Integer> used = new HashSet<>();
        // 单次循环
        for (int i = startIndex; i < nums.length; i++) {
            // 这个元素在本层使用过 || 这个元素小于 path 中最后一个元素，就放弃，继续遍历下一个元素
            if ((!path.isEmpty() && (path.get(path.size() - 1) > nums[i])) || used.contains(nums[i])) {
                continue;
            }
            // 记录这个元素在本层使用过，每一层都是一个新的 set 同一层共用一个 set
            used.add(nums[i]);
            path.add(nums[i]);
            backTracing(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 1154. 一年中的第几天
     * <p>
     * YYYY-MM-DD
     *
     * @param date
     * @return
     */
    public int dayOfYear(String date) {
        int sum = 0;
        List<Integer> monthList = new ArrayList<>();
        monthList.add(31);
        monthList.add(28);
        monthList.add(31);
        monthList.add(30);
        monthList.add(31);
        monthList.add(30);
        monthList.add(31);
        monthList.add(31);
        monthList.add(30);
        monthList.add(31);
        monthList.add(30);
        int year = Integer.parseInt(date.substring(0, 4));

        if ((year % 100 == 0 && year % 400 == 0) || (year % 100 != 0 && year % 4 == 0)) {
            monthList.set(1, 29);
        }
        int month = Integer.parseInt(date.substring(5, 7));
        for (int i = 0; i < month - 1; i++) {
            sum += monthList.get(i);
        }
        return sum + Integer.parseInt(date.substring(8));
    }
}

