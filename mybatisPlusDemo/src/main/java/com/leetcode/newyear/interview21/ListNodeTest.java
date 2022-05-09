package com.leetcode.newyear.interview21;

import javax.validation.constraints.Max;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 链表相关
 *
 * @author fdn
 * @since 2022-02-21 16:35
 */
public class ListNodeTest {

    /**
     * 24. 两两交换链表中的节点
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = head;
        ListNode pre = dummy;
        while (pre.next != null && node.next != null) {
            pre.next = node.next;
            node.next = node.next.next;
            pre.next.next = node;
//            pre = pre.next.next;
//            node = pre.next;
            pre = node;
            node = node.next;
        }
        return dummy.next;
    }

    /**
     * 23. 合并K个升序链表
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode next = dummy;
        ListNode node;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode list : lists) {
            if (list != null) {
                pq.offer(list);
            }
        }
        while (!pq.isEmpty()) {
            ListNode poll = pq.poll();
            node = poll;
            next.next = node;
            next = next.next;
            if (poll.next != null) {
                pq.offer(poll.next);
            }
            next.next = null;
        }
        return dummy.next;
    }

    /**
     * 25. K 个一组翻转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode node = head;
        ListNode leader = dummy;
        int n = k;
        while (node != null) {
            ListNode cur = node;
            ListNode first = node;
            n = k;
            while (n-- > 1 && node != null) {
                node = node.next;
            }
            // 处理最后一段的节点个数小于 k 的情况
            if (node == null) {
                break;
            } else {
                n = k;
                ListNode pre = null;
                ListNode temp;
                while (n-- > 0) {
                    temp = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = temp;
                }
                leader.next.next = cur;
                leader.next = pre;
                leader = first;
            }
            node = cur;
        }
        return dummy.next;
    }

    /**
     * 61. 旋转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        // 先求链表长度
        ListNode node = head;
        // 末尾节点
        ListNode tail = head;
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int size = 1;
        while (tail.next != null) {
            size++;
            tail = tail.next;
        }
        k %= size;
        if (k == 0) {
            return head;
        }
        k = size - k;
        // 第一段的最后一个节点
        ListNode pre = new ListNode(0);
        // node 是第二段的第一个节点
        pre.next = node;
        while (k-- > 0) {
            pre = pre.next;
            node = node.next;
        }
        pre.next = null;
        tail.next = head;
        return node;
    }

    /**
     * 92. 反转链表 II
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node = dummy;
        while (right-- > 0) {
            node = node.next;
        }
        ListNode preRight = node;
        ListNode headRight = preRight.next;
        node = dummy;
        while (left-- > 1) {
            node = node.next;
        }
        ListNode preLeft = node;
        ListNode headLeft = preLeft.next;

        preLeft.next = null;
        preRight.next = null;

        ListNode cur = headLeft;
        ListNode pre = null;
        ListNode temp;

        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        preLeft.next = pre;
        headLeft.next = headRight;
        return dummy.next;
    }

    /**
     * 剑指 Offer II 024. 反转链表
     * 有递归和迭代两种方法，一点一点的反转
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp;
        while (cur != null) {
            // 先将后面的节点存储起来，因为要改变 cur 的指向了
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public ListNode reverseList3(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }


    /**
     * 递归反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return temp;
    }


    /**
     * 160. 相交链表
     * 应该可以用 hashMap 来处理，但是空间复杂度不符合进阶要求；不过不知道能不能行诶，应该是可以的
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int sizeA = 0;
        ListNode nodeA = headA;
        while (nodeA != null) {
            nodeA = nodeA.next;
            sizeA++;
        }
        int sizeB = 0;
        ListNode nodeB = headB;
        while (nodeB != null) {
            nodeB = nodeB.next;
            sizeB++;
        }
        int gap = Math.abs(sizeA - sizeB);
        nodeA = headA;
        nodeB = headB;
        while (gap-- > 0) {
            if (sizeA > sizeB) {
                nodeA = nodeA.next;
            } else {
                nodeB = nodeB.next;
            }
        }
        while (nodeA != null && nodeB != null) {
            if (nodeA.equals(nodeB)) {
                return nodeA;
            }
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }
        return null;
    }


    public static void main(String[] args) {
        ListNodeTest listNodeTest = new ListNodeTest();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
//        listNodeTest.reverseKGroup(node1, 2);
        listNodeTest.reverseList(node1);
    }


    public static class ListNode {
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
