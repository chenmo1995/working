package com.leetcode.december;

import java.util.*;

/**
 * @author fdn
 * @since 2021-12-25 15:37
 */
public class Test1225TreeNode {

    /**
     * 1609. 奇偶树
     * 判断每一层节点的属性，需要使用广度优先遍历 BFS
     *
     * @param root
     * @return
     */
    public boolean isEvenOddTree(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerLast(root);
        // 记录第一层是偶数层 level = 0
        int level = 0;
        while (!deque.isEmpty()) {
            // 偶数编号层，都是奇数，且从左到右严格递增
            // 奇数编号层，都是偶数，且从左到右严格递减
            int prev = level % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            int size = deque.size();
            // 开始遍历当前队列中，属于当前层的节点
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                int cur = node.val;
                // 偶数编号层，节点值是偶数或者节点值没有严格递增
                if ((level % 2 == 0) && (cur % 2 == 0 || prev >= cur)) {
                    return false;
                }
                // 奇数编号层，节点值是奇数或者节点值没有严格递减
                if ((level % 2 == 1) && (cur % 2 == 1 || prev <= cur)) {
                    return false;
                }
                // 更新上一个节点值为当前遍历的节点值
                prev = cur;
                // 处理完一个节点之后，需要把他的左右子节点从尾部加入队列
                if (Objects.nonNull(node.left)) {
                    deque.offerLast(node.left);
                }
                if (Objects.nonNull(node.right)) {
                    deque.offerLast(node.right);
                }
            }
            level++;
        }
        return true;
    }

    /**
     * 144.二叉树的前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preTraversal(list, root);
        return list;
    }

    private void preTraversal(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preTraversal(list, node.left);
        preTraversal(list, node.right);
    }

    /**
     * 145.二叉树的后序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postTraversal(list, root);
        return list;
    }

    private void postTraversal(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        postTraversal(list, node.left);
        postTraversal(list, node.right);
        list.add(node.val);
    }

    /**
     * 144.
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        if (root != null) {
            deque.offerLast(root);
        }
        while (!deque.isEmpty()) {
            TreeNode node = deque.pollLast();
            if (node == null) {
                continue;
            }
            result.add(node.val);
            if (node.right != null) {
                deque.offerLast(node.right);
            }
            if (node.left != null) {
                deque.offerLast(node.left);
            }
        }
        return result;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        // 指针指向当前访问的节点
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                // 将访问的节点入栈
                stack.push(cur);
                // 继续往左子节点深入，直到左子结点为空，将左子结点的值加入 result
                cur = cur.left;
            } else {
                // 当前访问的节点为空，退回去访问栈顶的节点
                cur = stack.pop();
                result.add(cur.val);
                // 每次访问到一个正确的节点，就期待下一个节点是他的右子节点（左中右）
                cur = cur.right;
            }
        }
        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            if (peek != null) {
                // 将该节点弹出，避免重复操作，因为后面调整顺序之后还会将当前节点再入栈
                // 按照 "右中左" 的顺序入栈，出栈的时候就是 左中右 了
                stack.pop();
                if (peek.right != null) {
                    stack.push(peek.right);
                }
                stack.push(peek);
                // 中节点访问过，但是还没有处理，加入空节点作为标记
                stack.push(null);
                if (peek.left != null) {
                    stack.push(peek.left);
                }
            } else {// 只有遇到了空节点，才将下一个节点放入结果集
                // 弹出空节点
                stack.pop();
                peek = stack.pop();
                result.add(peek.val);
            }
        }
        return result;
    }

    /**
     * 迭代法  统一写法    前序
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            if (peek != null) {
                // 将该节点弹出，避免重复操作，因为后面调整顺序之后还会将当前节点再入栈
                // 按照 "右左中" 的顺序入栈，出栈的时候就是 中左右 了
                stack.pop();
                stack.push(peek);
                // 中节点访问过，但是还没有处理，加入空节点作为标记
                stack.push(null);
                if (peek.right != null) {
                    stack.push(peek.right);
                }
                if (peek.left != null) {
                    stack.push(peek.left);
                }
            } else {// 只有遇到了空节点，才将下一个节点放入结果集
                // 弹出空节点
                stack.pop();
                peek = stack.pop();
                result.add(peek.val);
            }
        }
        return result;
    }

    /**
     * 层序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> levelOrder2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        if (root != null) {
            deque.offerLast(root);
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                result.add(node.val);
                if (node.left != null) {
                    deque.offerLast(node.left);
                }
                if (node.right != null) {
                    deque.offerLast(node.right);
                }
            }
        }
        return result;
    }

    /**
     * 102. 二叉树的层序遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque();
        if (root != null) {
            deque.offerLast(root);
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                level.add(node.val);
                if (node.left != null) {
                    deque.offerLast(node.left);
                }
                if (node.right != null) {
                    deque.offerLast(node.right);
                }
            }
            result.add(new ArrayList<>(level));
            level.clear();
        }
        return result;
    }

    /**
     * 107. 二叉树的层序遍历 II
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque();
        if (root != null) {
            deque.offerLast(root);
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                level.add(node.val);
                if (node.left != null) {
                    deque.offerLast(node.left);
                }
                if (node.right != null) {
                    deque.offerLast(node.right);
                }
            }
            result.add(0, new ArrayList<>(level));
            level.clear();
        }
        return result;
    }

    /**
     * 199. 二叉树的右视图
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque();
        if (root != null) {
            deque.offerLast(root);
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                if (i == size - 1) {
                    result.add(deque.peekFirst().val);
                }
                TreeNode node = deque.pollFirst();
                if (node.left != null) {
                    deque.offerLast(node.left);
                }
                if (node.right != null) {
                    deque.offerLast(node.right);
                }
            }
        }
        return result;
    }

    /**
     * 637. 二叉树的层平均值
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque();
        if (root != null) {
            deque.offerLast(root);
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                if (node.left != null) {
                    deque.offerLast(node.left);
                }
                if (node.right != null) {
                    deque.offerLast(node.right);
                }
                sum += node.val;
            }
            result.add(sum / size);
        }
        return result;
    }

    /**
     * 226. 翻转二叉树   层序遍历
     *
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        if (root != null) {
            deque.offerLast(root);
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                if (node.left != null) {
                    deque.offerLast(node.left);
                }
                if (node.right != null) {
                    deque.offerLast(node.right);
                }
            }
        }
        return root;
    }

    /**
     * 226. 翻转二叉树   递归
     * 返回值的话其实也不需要，但是题目中给出的要返回 root 节点的指针，
     * 可以直接使用题目定义好的函数，所以就函数的返回类型为 TreeNode。
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        // 终止条件
        if (root == null) {
            return root;
        }
        // 中
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // 左
        invertTree(root.left);
        // 右
        invertTree(root.right);
        return root;
    }

    /**
     * 124. 二叉树中的最大路径和
     * <p>
     * 选取一个节点，包括他的路径，要么上，要么下，并且，选择了下之后不能选择上了；
     * 所以路径一定有一个最高点，左子树只能往下，右子树也只能往下
     *
     * @param root
     * @return
     */
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        // 节点的最大贡献值
        maxGain(root);
        return max;
    }

    /**
     * 当前节点的最大贡献值：node.val + max(left, right)
     *
     * @param node
     * @return
     */
    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 不会为负值，为负值就不选了；因为后面计算最大路径的时候还要加他，所以设为 0
        int leftMaxGain = Math.max(maxGain(node.left), 0);
        int rightMaxGain = Math.max(maxGain(node.right), 0);
        // 当前节点为最高点的最大路径
        int nodeGain = leftMaxGain + rightMaxGain + node.val;
        max = Math.max(max, nodeGain);
        return Math.max(leftMaxGain, rightMaxGain) + node.val;
    }

    /**
     * 226. 翻转二叉树   迭代  前序
     *
     * @param root
     * @return
     */
    public TreeNode invertTree3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            if (peek != null) {
                stack.pop();
                // 右左中
                if (peek.right != null) {
                    stack.push(peek.right);
                }
                if (peek.left != null) {
                    stack.push(peek.left);
                }
                stack.push(peek);
                stack.push(null);
            } else {
                stack.pop();
                TreeNode pop = stack.pop();
                TreeNode temp = pop.left;
                pop.left = pop.right;
                pop.right = temp;
            }
        }
        return root;
    }

    /**
     * 101. 对称二叉树   递归
     * 观察什么叫对称，并不是每个节点的左右对称。其实只是问根加点的两棵子树是不是对称
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return symmetry(root.left, root.right);
    }

    private boolean symmetry(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        } else {
            // 左 右 开始递归
            return symmetry(left.left, right.right) && symmetry(left.right, right.left);
        }
    }

    /**
     * 101. 对称二叉树   迭代
     * 可以用类似层序遍历的方法，每一层，最左最右两个节点开始比较
     * <p>
     * 注意，使用双端队列的时候不能两头插入，因为 [1,2,2,null,3,null,3] 会判断为 true；
     * 其实不是，是因为我在插入的时候都做了判空处理，这是不能做的
     * ArrayDeque::offer 插入元素是空的时候会报空指针，不能用
     * LinkedList 就不会报控制针
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root.left);
        deque.offer(root.right);
        while (!deque.isEmpty()) {
            TreeNode leftNode = deque.pollFirst();
            TreeNode rightNode = deque.pollFirst();
            if (leftNode == null && rightNode == null) {
                continue;
            }
            if ((leftNode == null && rightNode != null) || (leftNode != null && rightNode == null)) {
                return false;
            }
            if (leftNode.val != rightNode.val) {
                return false;
            }
            deque.offer(leftNode.right);
            deque.offer(rightNode.left);
            deque.offer(leftNode.left);
            deque.offer(rightNode.right);
        }
        return true;
    }

    /**
     * 104. 二叉树的最大深度        层序遍历
     * 显然，用层序遍历可以找到最深的地方
     * 用递归当然也可以，在全局维护一个 max 记录当前递归深度，一边递归一边更新即可
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                if (node.left != null) {
                    deque.offerLast(node.left);
                }
                if (node.right != null) {
                    deque.offerLast(node.right);
                }
            }
            level++;
        }
        return level;
    }

    /**
     * * 104. 二叉树的最大深度        递归
     *
     * @param root
     * @return
     */
    int max1 = 0;

    public int maxDepth(TreeNode root) {

        preTraversal2(root, 0);
        return max1;
    }

    private void preTraversal2(TreeNode node, int deep) {
        // 终止条件，node == null ，更新一下max
        if (node == null) {
            max1 = Math.max(max1, deep);
            return;
        }
        preTraversal2(node.left, deep + 1);
        preTraversal2(node.right, deep + 1);
    }

    /**
     * 111. 二叉树的最小深度
     * <p>
     * 二叉树的最小深度是从根节点到最近叶子节点的最短路径上的节点数量
     *
     * @param root
     * @return
     */
    int min = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        preTraversal3(root, 0);
        return min;
    }

    private void preTraversal3(TreeNode root, int deep) {
        // 到叶子节点了
        if (root.left == null && root.right == null) {
            min = Math.min(min, deep + 1);
            return;
        }
        if (root.left != null) {
            preTraversal3(root.left, deep + 1);
        }
        if (root.right != null) {
            preTraversal3(root.right, deep + 1);
        }
    }


    /**
     * 222. 完全二叉树的节点个数
     * 重点是找到最后一层的最后一个节点，但是怎么找呢？思路错了，找不到的
     * <p>
     * 转换思路，当前树不是满二叉树，可以找他的左右子树，看是不是满二叉树
     * 如果还不是，继续找。找到满二叉树了，子树节点数就好求了：2^n - 1
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        return countSonTreeNodes(root);
    }

    private int countSonTreeNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftCount = 0;
        int rightCount = 0;
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        while (leftNode != null) {
            leftCount++;
            leftNode = leftNode.left;
        }
        while (rightNode != null) {
            rightCount++;
            rightNode = rightNode.right;
        }
        // 当前节点代表的子树是满二叉树，节点数为 左右子树的节点加上当前节点
        if (leftCount == rightCount) {
            return (int) (Math.pow(2, leftCount) - 1) * 2 + 1;
        } else {
            // leftNode 和 rightNode 已经在遍历的时候改变了；要使用 node.left
            return countSonTreeNodes(node.left) + countSonTreeNodes(node.right) + 1;
        }
    }

    /**
     * 110. 平衡二叉树
     * 直接遍历每一个节点的左右子树高度好了
     * <p>
     * 这里很巧妙哟，用了两个递归，一个递归求节点高度，一个递归求该节点对应的树是不是平衡二叉树
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftDeep = nodeDeep(root.left);
        int rightDeep = nodeDeep(root.right);
        if (Math.abs(leftDeep - rightDeep) <= 1) {
            return isBalanced(root.left) && isBalanced(root.right);
        } else {
            return false;
        }
    }

    private int nodeDeep(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(nodeDeep(node.left), nodeDeep(node.right)) + 1;
    }

    /**
     * 257. 二叉树的所有路径    递归
     *
     * @param root
     * @return
     */
    List<String> result;
    StringBuilder path;

    public List<String> binaryTreePaths(TreeNode root) {
        result = new ArrayList<>();
        path = new StringBuilder();
        backTracing(root);
        return result;
    }

    private void backTracing(TreeNode root) {
        if (root == null) {
            return;
        }
        path.append(root.val);
        if (root.left == null && root.right == null) {
            result.add(path.toString());
            return;
        }
        // 其实这两步不用判空，因为在下一层递归一开始就校验了是否为空
        if (root.left != null) {
            path.append("->");
            backTracing(root.left);
            path = new StringBuilder(path.substring(0, path.lastIndexOf("->")));
        }
        if (root.right != null) {
            path.append("->");
            backTracing(root.right);
            path = new StringBuilder(path.substring(0, path.lastIndexOf("->")));
        }
    }

    /**
     * 257. 二叉树的所有路径    迭代
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        result = new ArrayList<>();
        Stack<Object> stack = new Stack<>();
        if (root == null) {
            return result;
        }
        stack.push(root);
        stack.push(root.val + "");
        while (!stack.isEmpty()) {
            String path = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();
            if (node.left == null && node.right == null) {
                result.add(path);
            }
            if (node.right != null) {
                stack.push(node.right);
                stack.push(path + "->" + node.right.val);
            }
            if (node.left != null) {
                stack.push(node.left);
                stack.push(path + "->" + node.left.val);
            }
        }
        return result;
    }

    /**
     * 100. 相同的树    递归
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null && q != null) {
            return false;
        } else if (p != null && q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    /**
     * 100. 相同的树    迭代
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        Stack<TreeNode> stack = new Stack<>();
        if (p == null && q == null) {
            return true;
        }
        stack.push(p);
        stack.push(q);
        while (!stack.isEmpty()) {
            TreeNode qNode = stack.pop();
            TreeNode pNode = stack.pop();
            if (qNode == null && pNode == null) {
                continue;
            }
            if (qNode == null && pNode != null) {
                return false;
            }
            if (qNode != null && pNode == null) {
                return false;
            }
            if (qNode.val != pNode.val) {
                return false;
            }
            stack.push(pNode.right);
            stack.push(qNode.right);
            stack.push(pNode.left);
            stack.push(qNode.left);
        }
        return true;
    }

    /**
     * 404. 左叶子之和       递归
     * 显然考察的是终止条件，也可以说收集时机
     *
     * @param root
     * @return
     */
    int result1;

    public int sumOfLeftLeaves(TreeNode root) {
        // 收集
        if (root == null) {
            return 0;
        }
        if (root.left != null && root.left.left == null && root.left.right == null) {
            result1 += root.left.val;
        }
        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);
        return result1;
    }

    /**
     * 404. 左叶子之和       迭代
     *
     * @param root
     * @return
     */
    public int sumOfLeftLeaves2(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        if (root == null) {
            return 0;
        }
        int result = 0;
        deque.offerLast(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.pollLast();
            if (node.left != null && node.left.left == null && node.left.right == null) {
                result += node.left.val;
            }
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
     * 513. 找树左下角的值
     * 不便利到最后一个节点，怎么知道当前层是最后一层呢，所以应该把节点的层数记下来
     * <p>
     * 层序遍历，把每一层的第一个节点记录下来，遍历结束之后取出该值
     * <p>
     * 递归怎么做呢
     * 前序遍历递归，记录当前节点的深度，一旦深度大于全局定义的深度变量 depth，更新 depth 的同时，更新 result
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue2(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        if (root == null) {
            return 0;
        }
        deque.offerLast(root);
        int result = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            // 记录每一层的第一个结点的值
            result = deque.peekFirst().val;
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.pollFirst();
                if (node.left != null) {
                    deque.offerLast(node.left);
                }
                if (node.right != null) {
                    deque.offerLast(node.right);
                }
            }
        }
        return result;
    }

    /**
     * 513. 找树左下角的值 递归
     *
     * @param root
     * @return
     */
    int depth = 0;
    int result2 = 0;

    public int findBottomLeftValue(TreeNode root) {
        getDepth(root, 0);
        return result2;
    }

    private void getDepth(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        // 叶子节点
        if (node.left == null && node.right == null) {
            if (level + 1 > depth) {
                depth = level + 1;
                result2 = node.val;
            }
        }
        getDepth(node.left, level + 1);
        getDepth(node.right, level + 1);
    }

    /**
     * 112. 路径总和
     * 这也是一个关于收集时机的问题
     *
     * @param root
     * @param targetSum
     * @return
     */
    int sum = 0;
    boolean flag = false;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        preTraversal4(root, targetSum);
        return flag;
    }

    private void preTraversal4(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        sum += root.val;
        if (root.left == null && root.right == null && sum == targetSum) {
            flag = true;
        }
        preTraversal4(root.left, targetSum);
        preTraversal4(root.right, targetSum);
        sum -= root.val;
    }

    /**
     * 113. 路径总和 II
     *
     * @param root
     * @param targetSum
     * @return
     */
    List<List<Integer>> result3;
    List<Integer> path2;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        result3 = new ArrayList<>();
        path2 = new ArrayList<>();
        if (root == null) {
            return result3;
        }
        backTracing2(root, targetSum);
        return result3;
    }

    private void backTracing2(TreeNode root, int targetSum) {
        if (root.left == null && root.right == null) {
            if (targetSum == root.val) {
                path2.add(root.val);
                result3.add(new ArrayList<>(path2));
                path2.remove(path2.size() - 1);
            }
            return;
        }
        if (root.left != null) {
            targetSum -= root.val;
            path2.add(root.val);
            backTracing2(root.left, targetSum);
            targetSum += root.val;
            path2.remove(path2.size() - 1);
        }
        if (root.right != null) {
            targetSum -= root.val;
            path2.add(root.val);
            backTracing2(root.right, targetSum);
            targetSum += root.val;
            path2.remove(path2.size() - 1);
        }
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder.length == 0) {
            return null;
        }
        // 遵循左闭右闭的循环不变量原则
        return buildTreeWithIndex2(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTreeWithIndex2(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postorderStart, int postorderEnd) {
        // 后序数组没有元素了
        if (postorderStart > postorderEnd) {
            return null;
        }
        // 拿到后序数组的最后一个节点，即为当前操作的根节点，也即中序数组的切割点
        int rootValue = postorder[postorderEnd];
        TreeNode root = new TreeNode(rootValue);
        // 只剩一个元素，直接返回了
        if (postorderStart == postorderEnd) {
            return root;
        }
        // 用 rootValue 去中序数组中进行切割
        int delimiter;
        for (delimiter = inorderStart; delimiter <= inorderEnd; delimiter++) {
            if (inorder[delimiter] == rootValue) {
                break;
            }
        }
        // 左闭右闭
        int leftInorderStart = inorderStart;
        int leftInorderEnd = delimiter - 1;
        int rightInorderStart = delimiter + 1;
        int rightInorderEnd = inorderEnd;
        // 以中序左数组右数组的长度去后序数组中分割出后序左数组右数组
        int leftPostorderStart = postorderStart;
        int leftPostorderEnd = postorderStart + (leftInorderEnd - leftInorderStart);
        int rightPostorderStart = leftPostorderEnd + 1;
        int rightPostorderEnd = postorderEnd - 1;

        // 递归
        root.left = buildTreeWithIndex2(inorder, leftInorderStart, leftInorderEnd, postorder, leftPostorderStart, leftPostorderEnd);
        root.right = buildTreeWithIndex2(inorder, rightInorderStart, rightInorderEnd, postorder, rightPostorderStart, rightPostorderEnd);
        return root;
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树     类似106
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder.length == 0) {
            return null;
        }
        // 遵循左闭右闭的循环不变量原则
        return buildTreeWithIndex(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);

    }

    private TreeNode buildTreeWithIndex(int[] preorder, int preorderStart, int preorderEnd, int[] inorder, int inorderStart, int inorderEnd) {
        // 前序数组没有元素了
        if (preorderStart > preorderEnd) {
            return null;
        }
        // 拿到前序数组的第一个节点，即为当前操作的根节点，也即中序数组的切割点
        int rootValue = preorder[preorderStart];
        TreeNode root = new TreeNode(rootValue);
        // 只剩一个元素，直接返回了
        if (preorderStart == preorderEnd) {
            return root;
        }
        // 用 rootValue 去中序数组中进行切割
        int delimiter;
        for (delimiter = inorderStart; delimiter <= inorderEnd; delimiter++) {
            if (inorder[delimiter] == rootValue) {
                break;
            }
        }
        // 左闭右闭
        int leftInorderStart = inorderStart;
        int leftInorderEnd = delimiter - 1;
        int rightInorderStart = delimiter + 1;
        int rightInorderEnd = inorderEnd;
        // 以中序左数组右数组的长度去前序数组中分割出前序左数组右数组
        int leftPreorderStart = preorderStart + 1;
        int leftPreorderEnd = leftPreorderStart + (leftInorderEnd - leftInorderStart);
        int rightPreorderStart = leftPreorderEnd + 1;
        int rightPreorderEnd = preorderEnd;

        // 递归
        root.left = buildTreeWithIndex(preorder, leftPreorderStart, leftPreorderEnd, inorder, leftInorderStart, leftInorderEnd);
        root.right = buildTreeWithIndex(preorder, rightPreorderStart, rightPreorderEnd, inorder, rightInorderStart, rightInorderEnd);
        return root;
    }

    /**
     * 654. 最大二叉树
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildMaxTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildMaxTree(int[] nums, int start, int end) {
        // 数组中没元素了
        if (start > end) {
            return null;
        }
        // 找到区间中最大的元素，记录元素位置
        int max = -1;
        int delimiter = 0;
        for (int i = start; i <= end; i++) {
            if (nums[i] > max) {
                max = nums[i];
                delimiter = i;
            }
        }
        TreeNode root = new TreeNode(nums[delimiter]);
        // 只剩一个元素
        if (start == end) {
            return root;
        }
        // 开始递归
        root.left = buildMaxTree(nums, start, delimiter - 1);
        root.right = buildMaxTree(nums, delimiter + 1, end);
        return root;
    }


    /**
     * 337. 打家劫舍 III
     * root的最大值分两种情况
     * - 选 root ，root.val + root.left.left + root.left.right + root.right.left + root.right.right
     * - 不选 root，root.left + root.right
     * <p>
     * 暴力搜索发现爷爷在计算自己能偷多少钱的时候，同时计算了 4 个孙子能偷多少钱，也计算了 2 个儿子能偷多少钱。
     * 这样在儿子当爷爷时，就会产生重复计算一遍孙子节点。
     * <p>
     * 所以用 记忆化搜索 存储了一些计算结果
     *
     * @param root
     * @return
     */
    Map<TreeNode, Integer> map = new HashMap<>();

    public int rob2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }
        int amount = 0;
        amount = root.val;
        if (root.right != null) {
            amount += rob2(root.right.left) + rob2(root.right.right);
        }
        if (root.left != null) {
            amount += rob2(root.left.left) + rob2(root.left.right);
        }
        int result = Math.max(amount, rob2(root.right) + rob2(root.left));
        map.put(root, result);
        return result;
    }

    /**
     * 337、打家劫舍     动态规划
     * 记忆化搜索，其实也用了动态规划的思想，由子节点的状态推断出父节点的状态；
     * 但是对于每一个节点，偷与不偷得到的最大金钱都没有做记录，而是需要实时计算，非常耗费性能
     * <p>
     * 在遍历二叉树的同时进行动态规划
     * {不取当前节点得到的钱, 取当前节点得到的钱}
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        int[] result = robTree(root);
        return Math.max(result[0], result[1]);
    }

    private int[] robTree(TreeNode root) {
        // 终止条件
        if (root == null) {
            return new int[]{0, 0};
        }
        // 后序遍历，左右中
        int[] left = robTree(root.left);
        int[] right = robTree(root.right);
        // 中节点
        int get = root.val + left[0] + right[0];
        int abandon = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{abandon, get};
    }

    public static void main(String[] args) {
        Test1225TreeNode test1225TreeNode = new Test1225TreeNode();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);

        node1.right = node2;
        test1225TreeNode.isSameTree2(node1, node1);
    }

    public static class TreeNode {
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
