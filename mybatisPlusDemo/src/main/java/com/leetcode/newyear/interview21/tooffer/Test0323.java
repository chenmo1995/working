package com.leetcode.newyear.interview21.tooffer;

import java.util.*;

/**
 * @author fdn
 * @since 2022-03-23 21:49
 */
public class Test0323 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else {
            return left.val == right.val && check(left.left, right.right) && check(left.right, right.left);
        }
    }

    /**
     * 144. 二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        deque.offerLast(root);
        // 前序遍历，中左右
        while (!deque.isEmpty()) {
            // 首先遍历的就是 中间节点
            TreeNode node = deque.pollLast();
            result.add(node.val);
            // 先放右，再放左，遍历的时候就是先遍历左，再遍历右
            if (node.right != null) {
                deque.offerLast(node.right);
            }
            if (node.left != null) {
                deque.offerLast(node.left);
            }
        }
        return result;
    }

    /**
     * 94. 二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                // 当前节点不为空，就需要去尝试找左子节点
                // 入栈
                stack.push(cur);
                // 继续访问左子节点
                cur = cur.left;
            } else {
                // 当前节点为空，撤回去访问前一个节点
                cur = stack.pop();
                // 打印当前节点（因为已经没有左子节点了）
                result.add(cur.val);
                // 再去访问当前节点（可以视为中间节点）的右子节点
                cur = cur.right;
            }
        }
        return result;
    }

    /**
     * 25. K 个一组翻转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = head;
        ListNode leader = dummy;
        while (cur != null) {
            ListNode node = cur;
            ListNode first = cur;
            // 记录第一个节点，翻转完了之后用它去链接下一组
            int i = k;
            while (node != null && i-- > 1) {
                node = node.next;
            }
            // node 是最后一个节点
            // cur 是第一个节点

            if (node == null) {
                // 剩余节点不够了
                break;
            } else {
                // 翻转
                i = k;
                ListNode pre = null;
                while (i-- > 0) {
                    ListNode temp = cur.next;
                    cur.next = pre;

                    pre = cur;
                    cur = temp;
                }
                // pre 是本组的头结点（翻转后），cur 是下一组的头结点。
                // 所以需要一个上一组的尾节点，和这一组的尾节点
                first.next = cur;
                leader.next = pre;
                // 下一组的 leader 是本组的最后一个节点（翻转后），或者是本组的第一个节点（翻转前）
                leader = first;
            }
            // 下一组的头结点就是 cur，继续下一组的翻转
        }
        // 返回第一次翻转的头结点
        return dummy.next;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
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
