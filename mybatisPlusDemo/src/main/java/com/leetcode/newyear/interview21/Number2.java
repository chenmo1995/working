package com.leetcode.newyear.interview21;

/**
 * 简单数据结构 & 简单算法
 * 21. 合并两个有序链表
 *
 * @author fdn
 * @since 2022-01-25 16:59
 */
public class Number2 {

    /*
    这就是归并排序呀
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode node1 = list1;
        ListNode node2 = list2;
        ListNode root = new ListNode();
        ListNode head = root;
        while (node1 != null && node2 != null) {
            while (node1 != null && node2 != null && node1.val <= node2.val) {
                head.next = new ListNode(node1.val);
                node1 = node1.next;
                head = head.next;
            }
            // node1.val > node2.val
            while (node1 != null && node2 != null && node1.val >= node2.val) {
                head.next = new ListNode(node2.val);
                node2 = node2.next;
                head = head.next;
            }
        }
        // node2 遍历完了
        while (node1 != null) {
            head.next = new ListNode(node1.val);
            node1 = node1.next;
            head = head.next;
        }
        // node1 遍历完了
        while (node2 != null) {
            head.next = new ListNode(node2.val);
            node2 = node2.next;
            head = head.next;
        }
        return root.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
