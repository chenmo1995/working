package com.leetcode.newyear.interview21;

/**
 * 既能考察对「数据结构」的掌握，还能考察对「递归函数」的设计 ...
 *
 * @author fdn
 * @since 2022-01-26 16:34
 */
public class Number3 {

    /**
     * 24. 两两交换链表中的节点
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode sentinel = new ListNode();
        sentinel.next = head;
        ListNode node;
        ListNode pre = sentinel;
        while (head != null && head.next != null) {
            // 保存第二个节点
            node = head.next;
            // 先把后面的保存下来
            ListNode rest = head.next.next;
            // 切断第二个节点和后面节点
            node.next = null;
            // 切断第一个节点和第二个节点
            head.next = null;
            // 切断头结点与第一个节点
            pre.next = null;
            // 第一个节点变成第二个节点的 next
            node.next = head;
            // 第一个节点的 next 接上后面的节点
            head.next = rest;
            // 头结点接上第二个节点
            pre.next = node;
            // 头结点和现在的第一个节点往后移
            pre = head;
            head = pre.next;
        }
        return sentinel.next;
    }

    public ListNode swapPairs2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while (head != null && head.next != null) {
            ListNode temp = head.next.next;

            pre.next = head.next;
            head.next.next = head;
            head.next = temp;
            pre = head;
            head = pre.next;
        }


        return dummy.next;
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
