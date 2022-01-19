package com.leetcode.december;

import java.util.*;

/**
 * @author fdn
 * @since 2021-12-28 16:14
 */
public class Test122803NTreeNode {

    /**
     * N 叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        Deque<Node> deque = new ArrayDeque<>();
        if (root != null) {
            deque.offerLast(root);
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Node node = deque.pollFirst();
                level.add(node.val);
                for (int j = 0; j < node.children.size(); j++) {
                    Node nodeChild = node.children.get(j);
                    if (nodeChild != null) {
                        deque.offerLast(nodeChild);
                    }
                }
            }
            result.add(new ArrayList<>(level));
            level.clear();
        }
        return result;
    }

    /**
     * 589. N 叉树的前序遍历   递归
     *
     * @param root
     * @return
     */
    public List<Integer> preorder1(Node root) {
        List<Integer> result = new ArrayList<>();
        dfs(result, root);
        return result;
    }

    private void dfs(List<Integer> result, Node node) {
        // 终止条件
        if (node == null) {
            return;
        }
        result.add(node.val);
        List<Node> children = node.children;
        for (int i = 0; i < children.size(); i++) {
            dfs(result, children.get(i));
        }
    }

    /**
     * 589. N 叉树的前序遍历   递归
     *
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            result.add(node.val);
            // 从右往左入栈，出栈的时候才是从左到右
            for (int i = node.children.size() - 1; i >= 0; i--) {
                Node temp;
                if ((temp = node.children.get(i)) != null) {
                    stack.push(temp);
                }
            }
        }
        return result;
    }

    /**
     * 559. N 叉树的最大深度
     *
     * @param root
     * @return
     */
    int max = 0;

    public int maxDepth(Node root) {
        preTraversal(root, 0);
        return max;
    }

    private void preTraversal(Node root, int deep) {
        if (root == null) {
            max = Math.max(max, deep);
            return;
        }
        // 这是比二叉树多出来的一步，很重要。二叉树的左右子节点为空，仍然可以空节点进入下一层递归
        // 但是 n 叉树不行，子树为空就进不了循环，所以提前判断一下
        if (root.children == null || root.children.isEmpty()) {
            max = Math.max(max, deep + 1);
            return;
        }
        for (int i = 0; i < root.children.size(); i++) {
            preTraversal(root.children.get(i), deep + 1);
        }
    }

    public static void main(String[] args) {
        Test122803NTreeNode nTreeNode = new Test122803NTreeNode();
        Node node = new Node(1);
        node.children = new ArrayList<>();
        nTreeNode.maxDepth(node);
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
