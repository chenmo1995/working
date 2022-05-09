package com.leetcode.newyear.interview21.tooffer;

import java.util.PriorityQueue;

/**
 * @author fdn
 * @since 2022-03-25 21:34
 */
public class Test0325 {

    /**
     * 912. 排序数组
     *
     * @param nums
     * @return
     */
    int[] arr;

    public int[] sortArray(int[] nums) {
        arr = nums;
        int n = nums.length;
        quickSort(0, n - 1);
        return arr;
    }

    private void quickSort(int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left, r = right;
        int standard = arr[l];
        while (l < r) {
            while (arr[r] >= standard && l < r) {
                r--;
            }
            arr[l] = arr[r];
            while (arr[l] <= standard && l < r) {
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = standard;
        quickSort(left, l - 1);
        quickSort(l + 1, right);
    }

    /**
     * 239. 滑动窗口最大值
     * <p>
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     * 1 <= k <= nums.length
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        // [元素的值，在数组中的位置]，先按元素的值排倒序，再按元素的位置排升序
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }
        ans[0] = pq.peek()[0];

        for (int i = k; i < n; i++) {
            pq.offer(new int[]{nums[i], i});
            while (!pq.isEmpty() && pq.peek()[1] < i - k + 1) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }

    /**
     * 剑指 Offer 18. 删除链表的节点
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next;
                break;
            }
            pre = pre.next;
        }
        return dummy.next;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
