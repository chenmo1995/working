package com.leetcode.newyear.weeklyrank;

import java.util.*;

/**
 * 第 283 场周赛
 *
 * @author fdn
 * @since 2022-03-06 10:26
 */
public class Test0306 {

    /**
     * 6016. Excel 表中某个范围内的单元格
     *
     * @param s
     * @return
     */
    public List<java.lang.String> cellsInRange(java.lang.String s) {
        List<java.lang.String> ans = new ArrayList<>();
        java.lang.String[] split = s.split(":");
        java.lang.String s1 = split[0];
        java.lang.String s2 = split[1];
        int a = s1.charAt(0);
        char a1 = s1.charAt(1);
        int b = s2.charAt(0);
        char b1 = s2.charAt(1);

        for (int i = a; i <= b; i++) {
            for (int j = a1; j <= b1; j++) {
                ans.add(java.lang.String.valueOf((char) i) + (j - '0'));
            }
        }
        return ans;
    }

    /**
     * 6017. 向数组中追加 K 个整数
     *
     * @param nums
     * @param k
     * @return
     */
    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);

        int index = findK(nums, k);
        if (index == -1) {
            return (1 + (long) k) * k / 2;
        }
        while (index != -1) {
            int index2 = findK(nums, k + index + 1);
            if (index2 > index) {
                index = index2;
            } else {
                long sum = sum(nums, index);
                return (1 + (long) k + index + 1) * (k + index + 1) / 2 - sum;
            }
        }
        return 0;
    }

    private long sum(int[] nums, int index) {
        long sum = 0;
        while (index >= 0) {
            sum += nums[index--];
        }
        return sum;
    }

    /**
     * 找到 <= k 的最后一个数
     *
     * @param nums
     * @param k
     * @return
     */
    private int findK(int[] nums, long k) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = (r - l + 1 >> 1) + l;
            int midVal = nums[mid];
            if (k >= midVal) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        if (nums[0] > k) {
            return -1;
        }
        return l;
    }

    /**
     * 6018. 根据描述创建二叉树
     *
     * @param descriptions
     * @return
     */
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Map<Integer, int[]> map2 = new HashMap<>();
        for (int[] description : descriptions) {
            TreeNode node = new TreeNode(description[1]);
            map.put(description[1], node);
            map2.put(description[1], description);
        }
        TreeNode root = null;
        for (Integer integer : map.keySet()) {
            int[] ints = map2.get(integer);
            TreeNode node;
            if (map.containsKey(ints[0])) {
                node = map.get(ints[0]);
            } else {
                if (root == null) {
                    root = new TreeNode(ints[0]);
                }
                node = root;
            }
            if (ints[2] == 1) {
                node.left = map.get(integer);
            } else {
                node.right = map.get(integer);
            }
            if (root == null || ints[0] != root.val) {
                map.put(ints[0], node);
            }
        }
        return root;
    }

    public static void main(java.lang.String[] args) {
        System.out.println(Integer.valueOf("1".charAt(0)));
        Test0306 test0306 = new Test0306();
        test0306.createBinaryTree(new int[][]{{20, 15, 1}, {20, 17, 0}, {50, 20, 1}, {50, 80, 0}, {80, 19, 1}});
        test0306.minimalKSum(new int[]{96, 44, 99, 25, 61, 84, 88, 18, 19,
                33, 60, 86, 52, 19, 32, 47, 35, 50, 94, 17, 29, 98, 22, 21, 72, 100, 40, 84, 35}, 35);
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
