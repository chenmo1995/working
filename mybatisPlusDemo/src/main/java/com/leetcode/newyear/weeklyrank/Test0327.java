package com.leetcode.newyear.weeklyrank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author fdn
 * @since 2022-03-27 10:34
 */
public class Test0327 {


    /**
     * 5268. 找出两数组的不同
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        Set<Integer> set2 = new HashSet<>();
        for (int i : nums2) {
            set2.add(i);
        }
        Set<Integer> list1 = new HashSet<>();
        Set<Integer> list2 = new HashSet<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i : nums1) {
            if (!set2.contains(i)) {
                list1.add(i);
            }
        }
        for (int i : nums2) {
            if (!set1.contains(i)) {
                list2.add(i);
            }
        }
        ans.add(new ArrayList<>(list1));
        ans.add(new ArrayList<>(list2));
        return ans;
    }

    /**
     * 5236. 美化数组的最少删除数
     * 是美化数组的最长子序列
     * <p>
     * 遍历 nums，只要当前数（index = 0）符合条件，就去遍历 index = 2 的数
     * 如果不符合，再去看下一个数（index = 1）
     *
     * @param nums
     * @return
     */
    public int minDeletion(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                ans++;
            } else {
                // 当前数和下一个数可以凑成合格的数对，就去访问下下个数
                i++;
            }
        }
        if ((n - ans) % 2 == 1) {
            ans++;
        }
        return ans;
    }

    /**
     * 5253. 找到指定长度的回文数
     *
     * @param queries
     * @param intLength
     * @return
     */
    public long[] kthPalindrome(int[] queries, int intLength) {
        // 一共有 10^((n-1)/2)*9 个回文数
        int cnt = (int) Math.pow(10, ((intLength - 1) / 2)) * 9;
        int n = queries.length;
        long[] ans = new long[n];

        for (int i = 0; i < n; i++) {
            if (queries[i] > cnt) {
                ans[i] = -1;
            } else {
                ans[i] = getNum(queries[i], intLength);
            }
        }
        return ans;
    }

    /**
     * 构造回文数
     * 回文数的前半部分其实是递增的，前 (n+1)/2 长度是递增的
     *
     * @param i
     * @param n
     * @return
     */
    private long getNum(int i, int n) {
        long left = (long) Math.pow(10, (n + 1) / 2 - 1) + i - 1;
        return getAnswer(left, n % 2 == 1);
    }

    private long getAnswer(long left, boolean isOdd) {
        StringBuilder sb = new StringBuilder(String.valueOf(left));
        StringBuilder right;
        if (isOdd) {
            String l = sb.substring(0, sb.length() - 1);
            right = new StringBuilder(l).reverse();
        } else {
            StringBuilder temp = new StringBuilder(sb);
            right = temp.reverse();
        }
        return Long.parseLong("" + sb + right);
    }

    public static void main(String[] args) {
        Test0327 test0327 = new Test0327();
        test0327.kthPalindrome(new int[]{2, 4, 6}, 4);
    }

    /**
     * dp[i][j] 表示 第 i+1 次，选中了第 j+1 个栈
     * <p>
     * dp[i][j]
     *
     * @param piles
     * @param k
     * @return
     */
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        return 0;
    }

}
