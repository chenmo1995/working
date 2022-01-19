package com.leetcode.newyear.january;

/**
 * @author fdn
 * @since 2022-01-16 14:19
 */
public class Test0116 {
    int size = 0;
    int[] nodeArray;

    public Test0116(ListNode head) {
        ListNode node = head;
        while (node != null) {
            size++;
            node = node.next;
        }
        nodeArray = new int[size];

        ListNode nodeVal = head;
        int index = 0;
        while (nodeVal != null) {
            nodeArray[index++] = nodeVal.val;
            nodeVal = nodeVal.next;
        }
    }

    public int getRandom() {
        return nodeArray[(int) (Math.random() * size)];
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