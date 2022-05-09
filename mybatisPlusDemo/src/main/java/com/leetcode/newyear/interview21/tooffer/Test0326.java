package com.leetcode.newyear.interview21.tooffer;

import java.util.Arrays;

/**
 * @author fdn
 * @since 2022-03-26 12:02
 */
public class Test0326 {

    /**
     * 475. 供暖器
     * 求最小加热半径，大于等于这个半径的一定所有都可以供暖，
     * 小于这个加热半径的一定不是所有都可以供暖
     *
     * @param houses
     * @param heaters
     * @return
     */
    int[] houses;
    int[] heaters;

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        Arrays.sort(houses);
        this.houses = houses;
        this.heaters = heaters;
        int l = 0, r = (int) 1e9;
        while (l < r) {
            int mid = (r - l >> 1) + l;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int radius) {
        // 要找到距离房子最近的供暖器
        for (int house : houses) {
            int index = findNearHeater(house);
            if (house >= heaters[index] - radius && house <= heaters[index] + radius) {
            } else {
                return false;
            }
        }
        return true;
    }

    private int findNearHeater(int target) {
        int n = heaters.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (r - l >> 1) + l;
            int midVal = heaters[mid];
            if (midVal >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        // 最后找到的是第一个 大于等于 target 的加热器位置
        if (l == 0) {
            return l;
        }
        if (l == n - 1) {
            if (target > heaters[l]) {
                return l;
            }
        }
        return heaters[l] - target > target - heaters[l - 1] ? l - 1 : l;
    }

    /**
     * 4. 寻找两个正序数组的中位数
     * 找到合并之后的索引为 k/2 和 (k-1)/2 的平均值
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        if ((m + n) % 2 == 0) {
            // 是第 k 大，不是索引为 k
            int a = findKth(nums1, 0, nums2, 0, (m + n) / 2);
            int b = findKth(nums1, 0, nums2, 0, (m + n) / 2 + 1);
            return (a + b) / 2.0;
        } else {
            return findKth(nums1, 0, nums2, 0, (m + n) / 2 + 1);
        }

    }

    /**
     * 以 i 为起点的 nums1 和以 j 为起点的 nums2 中找到【第 k 大】的值
     *
     * @param nums1
     * @param i
     * @param nums2
     * @param j
     * @param k
     * @return
     */
    private int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        // 确保 nums1 剩余的数少一点
        if (nums1.length - i > nums2.length - j) {
            return findKth(nums2, j, nums1, i, k);
        }
        // nums1 中没有待选元素了
        if (i >= nums1.length) {
            // j + k - 1 到 j，一共有 k 个数，也就是第 k 大的数
            return nums2[j + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[i], nums2[j]);
        } else {
            // nums1 从 i 开始，往后第 k/2 个数  index = i + k/2 - 1 【距离 i 就有 k/2 个数】，有越界的风险
            // nums2 从 j 开始，往后第 k - k/2 个数（可能会比 nums1 多一个数） index = j + k - k/2 - 1
            int index1 = Math.min(i + k / 2 - 1, nums1.length - 1);
            int index2 = j + k - k / 2 - 1;
            if (nums1[index1] > nums2[index2]) {
                // 第 k 大的数肯定不在 [j, index2 + 1] 中了，只可能在 [i, n) 和 [index2 + 1, m)
                return findKth(nums1, i, nums2, index2 + 1, k - (index2 - j + 1));
            } else {
                // 第 k 大的数肯定不在 [i, index1 + 1] 中了，只可能在 [j, m) 和 [index1 + 1, n)
                return findKth(nums1, index1 + 1, nums2, j, k - (index1 - i + 1));
            }
        }
    }

    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     *
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        int n = nums.length;
        int i = 0, j = n - 1;
        while (i < j) {
            while (i < j && nums[i] % 2 == 1) {
                i++;
            }
            // nums[i] 为偶数，需要调到最后面去
            while (j > i && nums[j] % 2 == 0) {
                j--;
            }
            // nums[j] 为奇数，跟 nums[i] 调换
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }
        return nums;
    }

    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (k-- > 0) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        Test0326 test0326 = new Test0326();
        test0326.findRadius(new int[]{1, 2, 3}, new int[]{2});
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
