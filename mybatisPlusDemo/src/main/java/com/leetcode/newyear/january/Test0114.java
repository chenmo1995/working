package com.leetcode.newyear.january;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author fdn
 * @since 2022-01-14 13:50
 */
public class Test0114 {

    /**
     * 373. 查找和最小的 K 对数字
     * 看起来跟归并排序很像，双指针，比较最小的数
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    boolean flag = true;

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;

        // 始终保持 nums1 的长度小，并且记录是否进行过交换，利用标记位保持后面的序列顺序
        if (n > m) {
            flag = false;
            return kSmallestPairs(nums2, nums1, k);
        }
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> (nums1[a[0]] + nums2[a[1]])));
        for (int i = 0; i < Math.min(n, k); i++) {
            q.add(new int[]{i, 0});
        }
        while (ans.size() < k && !q.isEmpty()) {
            int[] poll = q.poll();
            int a = poll[0], b = poll[1];
            List<Integer> path = new ArrayList<>();
            path.add(flag ? nums1[a] : nums2[b]);
            path.add(flag ? nums2[b] : nums1[a]);
            ans.add(new ArrayList<>(path));
            if (b + 1 < m) {
                q.add(new int[]{a, b + 1});
            }
        }
        return ans;
    }

    public int[] mergeSort(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[] nums = new int[n + m];

        int a = 0, b = 0, index = 0;
        while (a < n && b < m) {
            while (a < n && nums1[a] <= nums2[b]) {
                nums[index++] = nums1[a++];
            }
            while (b < m && nums2[b] <= nums1[a]) {
                nums[index++] = nums2[b++];
            }
        }
        if (a == n) {
            while (b < m) {
                nums[index++] = nums2[b++];
            }
        }
        if (b == m) {
            while (a < n) {
                nums[index++] = nums1[a++];
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        Test0114 test0114 = new Test0114();
        int[] ints = test0114.mergeSort(new int[]{1, 3, 4, 5, 6, 6, 7, 7, 9}, new int[]{0, 1, 3, 3, 4, 5, 8, 8});
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
    }
}
