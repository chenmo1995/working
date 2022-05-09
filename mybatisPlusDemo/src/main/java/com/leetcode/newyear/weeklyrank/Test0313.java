package com.leetcode.newyear.weeklyrank;


import java.util.*;

/**
 * @author fdn
 * @since 2022-03-13 10:32
 */
public class Test0313 {

    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        List<Integer> sortList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == key) {
                sortList.add(i);
            }
        }
        for (int i = 0; i < n; i++) {
            // index 是与 i 最近的 key 的坐标
            int index = indexOf(sortList, i);
            if (Math.abs(index - i) <= k) {
                list.add(i);
            }
        }
        return list;
    }

    private int indexOf(List<Integer> list, int i) {
        int n = list.size();
        int l = 0, r = n - 1;
        // 求出来的是大于等于 i 的最后一个数
        while (l < r) {
            int mid = (r - l >> 1) + l;
            int midVal = list.get(mid);
            if (midVal >= i) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (l == 0) {
            return list.get(0);
        }
        //[2, 5]
        if (Math.abs(list.get(l - 1) - i) < Math.abs(list.get(l) - i)) {
            return list.get(l - 1);
        } else {
            return list.get(l);
        }
    }

    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        int ans = 0;
        int[][] grid = new int[n][n];
        for (int[] ints : dig) {
            grid[ints[0]][ints[1]] = 1;
        }
        for (int[] artifact : artifacts) {
            int a1 = artifact[0];
            int b1 = artifact[1];
            int a2 = artifact[2];
            int b2 = artifact[3];

            boolean flag = false;
            for (int i = a1; i <= a2; i++) {
                for (int j = b1; j <= b2; j++) {
                    if (grid[i][j] != 1) {
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                ans++;
            }
        }
        return ans;
    }

    public int maximumTop(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) {
            if (k % 2 == 1) {
                return -1;
            } else {
                return nums[0];
            }
        }
        // 4    5
        int index = Math.min(k - 2, n - 1);
        int max = -1;
        for (int i = 0; i <= index; i++) {
            max = Math.max(max, nums[i]);
        }
        if (k <= n - 1) {
            max = Math.max(nums[k], max);
        }
        return max;
    }

    public static void main(java.lang.String[] args) {
        // TODO document why this method is empty
        System.out.println("");
    }
}
