package com.leetcode.newyear.interview21;

import java.util.*;


/**
 * @author fdn
 * @since 2022-02-07 16:05
 */
public class Dfs_02 {


    /**
     * 987. 二叉树的垂序遍历
     * 好像构造一个优先队列就可以解决诶
     * PriorityQueue<value, col ,row>, 按 col 顺序排列
     *
     * @param root
     * @return
     */
    PriorityQueue<int[]> pq;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        // 按照 col 排顺序；如果 col 相同，按 row 排顺序；如果 row 相同，按 val 排顺序
        pq = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[2] == b[2] ? a[0] - b[0] : a[2] - b[2] : a[1] - b[1]);
        dfs(root, 0, 0);
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int col = cur[1];
            List<Integer> path = new ArrayList<>();
            path.add(cur[0]);
            while (!pq.isEmpty() && pq.peek()[1] == col) {
                int[] next = pq.poll();
                path.add(next[0]);
            }
            result.add(new ArrayList<>(path));
        }
        return result;
    }

    private void dfs(TreeNode node, int col, int row) {
        // 终止条件
        if (node == null) {
            return;
        }
        dfs(node.left, col - 1, row + 1);
        pq.offer(new int[]{node.val, col, row});
        dfs(node.right, col + 1, row + 1);
    }

    /**
     * 1609. 奇偶树
     *
     * @param root
     * @return
     */
    // <level, val> 记录每一层上一次遍历到的节点的值
    Map<Integer, Integer> map = new HashMap<>(16);

    public boolean isEvenOddTree(TreeNode root) {
        return dfs2(root, 0);
    }

    private boolean dfs2(TreeNode node, int level) {
        if (node == null) {
            return true;
        }
        int pre = map.getOrDefault(level, level % 2 == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE);
        // 不符合
        if (level % 2 == 1 && (node.val % 2 == 1 || pre <= node.val)) {
            return false;
        }
        if (level % 2 == 0 && (node.val % 2 == 0 || pre >= node.val)) {
            return false;
        }
        map.put(level, node.val);

        return dfs2(node.left, level + 1) && dfs2(node.right, level + 1);
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
}

