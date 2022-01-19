package com.leetcode.december;

import java.util.*;

/**
 * @author fdn
 * @since 2021-12-17 15:04
 */
public class Test1217 {

    /**
     * 347. 前 K 个高频元素
     * <p>
     * 优先队列好像可以，按照元素的频次取最大值。但是复杂度好像比较高，试一下
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] max = new int[k];
        // Map<元素,元素的频次>，以频次顺序排序
        Map<Integer, Integer> map = new HashMap<>(16);
        // 优先队列中值是map的key，也就是元素本身
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) ->
                !map.getOrDefault(i1, 0).equals(map.getOrDefault(i2, 0))
                        ? map.getOrDefault(i1, 0) - map.getOrDefault(i2, 0)
                        : i2 - i1);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (pq.size() == k) {
                if (entry.getValue() > map.get(pq.peek())) {
                    pq.poll();
                    pq.offer(entry.getKey());
                } else {
                    //直接丢弃
                }
            } else {
                pq.offer(entry.getKey());
            }
        }
        for (int i = 0; i < k; i++) {
            // size在变化
            max[i] = pq.poll();
        }
        return max;
    }

    public static void main(String[] args) {
        Test1217 test1217 = new Test1217();
        List<List<Integer>> combine = test1217.combine(4, 2);
        System.out.println(combine);
    }

    /**
     * 77. 组合
     *
     * @param n
     * @param k
     * @return
     */
    // 用来存放符合条件的结果的集合
    List<List<Integer>> result = new ArrayList<>();
    // 用来存放 单个 符合条件的结果
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {

        backTracing(n, k, 1);

        return result;
    }

    private void backTracing(int n, int k, int startIndex) {
        // 剪枝：path 长度加上区间 [startIndex, n] 的长度小于 k，不可能构造出长度为 k 的 path
        if (path.size() + (n - startIndex + 1) < k) {
            return;
        }
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            // 这一步一定不行写成 result.add(path),因为后面会对path.remove，会改变path
            // 对应的，result 中 add 的 path 也就改变了
            return;
        }
        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            backTracing(n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }


    /**
     * 216. 组合总和 III
     *
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {

        backTracing2(n, k, 1);
        return result;
    }

    private void backTracing2(int n, int k, int startIndex) {
        // 剪枝
        // 1、长度大于k；2、长度小于k，剩余个数小于k-size
        // 3、和大于n；  4、和加剩余的和，小于n
        // 这一步剪枝可以用，但是每次回溯都要求和，非常耗费性能，建议在回溯里面把上一次的和加上去。
        // 然后在处理节点的时候 sum+i，再在回溯撤销处理结果的那一步中把i弹出path的同时，在sum中也减去i
        if (path.size() > k || (path.size() < k && (9 - startIndex + 1) < k - path.size())) {
            return;
        }
        if (path.stream().reduce(0, (a, b) -> a + b) > n || (startIndex + 9) * (9 - startIndex + 1) / 2 + path.stream().reduce(0, (a, b) -> a + b) < n) {
            return;
        }
        if (path.stream().reduce(0, (a, b) -> a + b) == n && path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= 9; i++) {
            path.add(i);
            backTracing2(n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }


}



















